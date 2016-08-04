package rome.database;

import javax.sql.DataSource;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import rome.model.base.Game;
import rome.model.base.Player;
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
            rs = st.executeQuery("SELECT player_id, first_name, last_name, nickname FROM players");
            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt(1));
                p.setFirstName(rs.getString(2));
                p.setLastName(rs.getString(3));
                p.setNickname(rs.getString(4));
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
            rs = pst.executeQuery();

            if (rs.next()) {
                teamID = rs.getInt("team_id");
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

    public List<Game> getGamesForDate(String datePlayed) {
        List<Game> games = new ArrayList<>();

        String getGames = "SELECT * FROM v_all_player_games WHERE date_played=?";

        try {
            PreparedStatement pst = conn.prepareStatement(getGames);
            pst.setString(1, datePlayed);
            rs = pst.executeQuery();
            Game currGame = new Game();
            Team win = new Team();
            Team lose = new Team();
            int index = 0;
            System.out.println("Query success");
            while (rs.next()) {
                index %= 4;
                if (index == 0) {
                    currGame = new Game();
                    win = new Team();
                    lose = new Team();
                }
                Player p = new Player();
                p.setId(rs.getInt(6));
                p.setFirstName(rs.getString(7));
                p.setLastName(rs.getString(8));
                p.setNickname(rs.getString(9));

                if (rs.getInt(5) == 1) {
                    win.addPlayer(p);
                    win.setScore(rs.getInt(4));
                    currGame.setWinColor(rs.getString(3));
                } else {
                    lose.addPlayer(p);
                    lose.setScore(rs.getInt(4));
                }

                if (index == 3) {
                    currGame.setWinner(win);
                    currGame.setLoser(lose);
                    currGame.setDate(rs.getString(2));
                    currGame.setGameID(rs.getInt(1));
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
            rs = pst.executeQuery();
            Game currGame = new Game();
            Team win = new Team();
            Team lose = new Team();
            int index = 0;

            while (rs.next()) {
                index %= 4;
                if (index == 0) {
                    currGame = new Game();
                    win = new Team();
                    lose = new Team();
                }
                Player p = new Player();
                p.setId(rs.getInt(5));
                p.setFirstName(rs.getString(6));
                p.setLastName(rs.getString(7));
                p.setNickname(rs.getString(8));

                if (rs.getInt(4) == 1) {
                    win.addPlayer(p);
                    currGame.setWinColor(rs.getString(3));
                } else {
                    lose.addPlayer(p);
                }

                if (index == 3) {
                    currGame.setWinner(win);
                    currGame.setLoser(lose);
                    currGame.setDate(rs.getString(2));
                    currGame.setGameID(rs.getInt(1));
                    games.add(currGame);
                }

            }

        } catch (SQLException s) {
            s.printStackTrace();
        }

        return games;
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
