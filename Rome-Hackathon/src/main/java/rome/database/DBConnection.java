package rome.database;

import javax.sql.DataSource;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import rome.model.base.Player;
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

    public void recordGame(int teamID, String winColor, int tid1, int score1, int tid2, int score2, int datePlayed) {
        String insertTeam = "CALL recordGame(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(insertTeam);
            pst.setInt(1, teamID);
            pst.setString(2, winColor);
            pst.setInt(3, tid1);
            pst.setInt(4, score1);
            pst.setInt(5, tid2);
            pst.setInt(6, score2);
            pst.setInt(7, datePlayed);
            pst.execute();
            pst.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
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
