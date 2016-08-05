package rome.database;

import javax.sql.DataSource;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import rome.model.base.Game;
import rome.model.base.Player;
import rome.model.base.Stats;
import rome.model.base.Team;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by borowicr on 8/3/16.
 */
public class DBConnection {

    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    public DBConnection() {
        String url = "jdbc:mysql://localhost:3306/CrimsonFoosball?useSSL=false";
        String user = "root";
        String pass = "crimsonFoos";
        try {
            conn = DriverManager.getConnection(url, user, pass);
            st = conn.createStatement();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public List<Player> getPlayersAsList() {
        List<Player> players = new ArrayList<>();

        try {
            ResultSet plys = st.executeQuery("SELECT player_id, first_name, last_name, nickname FROM players");
            while (plys.next()) {
                Player p = new Player();
                p.setId(plys.getInt(1));
                p.setFirstName(plys.getString(2));
                p.setLastName(plys.getString(3));
                p.setNickname(plys.getString(4));
                p.setStats(this.getPlayerStats(plys.getInt(1)));
                players.add(p);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return players;

    }

    public void addNewPlayer(Player newPlayer) {
        String insertPlayer = "INSERT INTO players (first_name, last_name, nickname)"
                            + "VALUES (?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(insertPlayer);
            pst.setString(1, newPlayer.getFirstName());
            pst.setString(2, newPlayer.getLastName());
            pst.setString(3, newPlayer.getNickname());
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public int getTeam(int pid1, int pid2) {
        int teamID = -1;

        String getTeam = "SELECT team_id FROM team_member_bridge WHERE player_id=? "
                       + "AND team_id IN (SELECT team_id FROM team_member_bridge WHERE player_id=?)";

        try {
            PreparedStatement pst = conn.prepareStatement(getTeam);
            pst.setInt(1, pid1);
            pst.setInt(2, pid2);
            ResultSet t = pst.executeQuery();

            if (t.next()) {
                teamID = t.getInt("team_id");
            }
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return teamID;
    }

    public void createTeam(String nickname, int pid1, int pid2) {
        String insertTeam = "CALL createTeam(?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(insertTeam);
            pst.setString(1, nickname);
            pst.setInt(2, pid1);
            pst.setInt(3, pid2);
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void recordGame(int teamID, String winColor, int tid1, int score1, int tid2, int score2) {
        String insertTeam = "CALL recordGame(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(insertTeam);
            pst.setInt(1, teamID);
            pst.setString(2, winColor);
            pst.setInt(3, tid1);
            pst.setInt(4, score1);
            pst.setInt(5, tid2);
            pst.setInt(6, score2);
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Stats getPlayerStats(int pid) {
        String getTotal = "SELECT COUNT(*) FROM v_all_player_games WHERE player_id=?";
        String getWins = "SELECT COUNT(*) FROM v_all_player_games WHERE player_id=? AND won=?";

        Stats stats = null;

        try {
            PreparedStatement pst = conn.prepareStatement(getTotal);
            PreparedStatement pstwin = conn.prepareStatement(getWins);
            pst.setInt(1, pid);
            ResultSet sts = pst.executeQuery();
            int total = 0;
            if (sts.next()) {
                stats = new Stats();
                total = sts.getInt(1);
                stats.setGamesPlayed(total);
            }

            pstwin.setInt(1, pid);
            pstwin.setInt(2, 1);
            ResultSet win = pstwin.executeQuery();
            if (win.next()) {
                int wins = win.getInt(1);
                stats.setWins(wins);
                stats.setLosses(total - wins);
            }

            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return stats;
    }

    public Stats getTeamStats(int teamID) {
        String getTotal = "SELECT COUNT(*) FROM v_all_games WHERE team_id=?";
        String getWins = "SELECT COUNT(*) FROM v_all_games WHERE team_id=? AND won=?";

        Stats stats = null;

        try {
            PreparedStatement pst = conn.prepareStatement(getTotal);
            PreparedStatement pstwin = conn.prepareStatement(getWins);
            pst.setInt(1, teamID);
            ResultSet sts = pst.executeQuery();

            int totalGames = 0;
            if (sts.next()) {
                stats = new Stats();
                totalGames = sts.getInt(1);
                stats.setGamesPlayed(totalGames);
            }

            pstwin.setInt(1, teamID);
            pstwin.setInt(2, 1);
            ResultSet win = pstwin.executeQuery();
            if (win.next()) {
                int wins = win.getInt(1);
                stats.setWins(wins);
                stats.setLosses(totalGames - wins);
            }

            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return stats;
    }



    public List<Game> getGamesForDate(String datePlayed) {
        List<Game> games = new ArrayList<>();

        String getGames = "SELECT * FROM v_all_player_games WHERE date_played=?";

        try {
            PreparedStatement pst = conn.prepareStatement(getGames);
            pst.setString(1, datePlayed);
            ResultSet gms = pst.executeQuery();
            Game currGame = new Game();
            Team win = new Team();
            Team lose = new Team();
            int index = 0;
            System.out.println("Query success");
            while (gms.next()) {
                index %= 4;
                if (index == 0) {
                    currGame = new Game();
                    win = new Team();
                    lose = new Team();
                }
                Player p = new Player();
                p.setId(gms.getInt(7));
                p.setFirstName(gms.getString(8));
                p.setLastName(gms.getString(9));
                p.setNickname(gms.getString(10));
                p.setStats(this.getPlayerStats(gms.getInt(7)));

                if (gms.getInt(6) == 1) {
                    win.addPlayer(p);
                    win.setScore(gms.getInt(4));
                    win.setTeamID(gms.getInt(5));
                    currGame.setWinColor(gms.getString(3));
                } else {
                    lose.addPlayer(p);
                    lose.setScore(gms.getInt(4));
                    lose.setTeamID(gms.getInt(5));
                }

                if (index == 3) {
                    win.setStats(this.getTeamStats(win.getTeamID()));
                    lose.setStats(this.getTeamStats(lose.getTeamID()));
                    currGame.setWinner(win);
                    currGame.setLoser(lose);
                    currGame.setDate(gms.getString(2));
                    currGame.setGameID(gms.getInt(1));
                    games.add(currGame);
                }

                index++;
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return games;
    }

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        String getGames = "SELECT * FROM v_all_player_games";

        try {
            PreparedStatement pst = conn.prepareStatement(getGames);
            ResultSet gms = pst.executeQuery();
            Game currGame = new Game();
            Team win = new Team();
            Team lose = new Team();
            int index = 0;

            while (gms.next()) {
                index %= 4;
                if (index == 0) {
                    currGame = new Game();
                    win = new Team();
                    lose = new Team();
                }
                Player p = new Player();
                p.setId(gms.getInt(7));
                p.setFirstName(gms.getString(8));
                p.setLastName(gms.getString(9));
                p.setNickname(gms.getString(10));
                p.setStats(this.getPlayerStats(gms.getInt(7)));

                if (gms.getInt(6) == 1) {
                    win.addPlayer(p);
                    win.setScore(gms.getInt(4));
                    win.setTeamID(gms.getInt(5));
                    currGame.setWinColor(gms.getString(3));
                } else {
                    lose.addPlayer(p);
                    lose.setScore(gms.getInt(4));
                    lose.setTeamID(gms.getInt(5));
                }

                if (index == 3) {
                    win.setStats(this.getTeamStats(win.getTeamID()));
                    lose.setStats(this.getTeamStats(lose.getTeamID()));
                    currGame.setWinner(win);
                    currGame.setLoser(lose);
                    currGame.setDate(gms.getString(2));
                    currGame.setGameID(gms.getInt(1));
                    games.add(currGame);
                }

            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        return games;
    }

    public void updatePlayer(int pid, String first, String last, String nickname) {
        String update = "UPDATE players SET first_name=?, last_name=?, nickname=? "
                + "WHERE player_id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setString(1, first);
            pst.setString(2, last);
            pst.setString(3, nickname);
            pst.setInt(4, pid);
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void deletePlayer(int pid) {
        String delete = "DELETE FROM players WHERE player_id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setInt(1, pid);
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Team getTeam(int teamID) {
        String getTeam = "SELECT * FROM teams JOIN team_member_bridge WHERE "
                       + "teams.team_id=team_member_bridge.team_id AND teams.team_id=?";
        String getPlayer = "SELECT * FROM players WHERE player_id=?";

        Team newTeam = null;
        try {
            PreparedStatement pst = conn.prepareStatement(getTeam);
            pst.setInt(1, teamID);

            ResultSet results = pst.executeQuery();
            newTeam = new Team();

            while (results.next()) {
                int pid = results.getInt(5);

                PreparedStatement play = conn.prepareStatement(getPlayer);
                play.setInt(1, pid);
                ResultSet player = play.executeQuery();
                if (player.next()) {
                    Player p = new Player();
                    p.setId(pid);
                    p.setFirstName(player.getString(6));
                    p.setLastName(player.getString(7));
                    p.setNickname(player.getString(8));
                    p.setStats(this.getPlayerStats(pid));
                    newTeam.addPlayer(p);
                }
                play.close();
            }

            newTeam.setStats(this.getTeamStats(teamID));

            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return newTeam;
    }





    /**
     * Close database connection
     */
    public void close() {
        try {
            conn.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
