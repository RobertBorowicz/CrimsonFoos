package rome.database;

import rome.model.base.Player;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by borowicr on 8/3/16.
 */
public class PlayersDAOImpl implements PlayersDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Player> getPlayers() {
        List<Player> players = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM players");

            while (rs.next()) {

                Player p = new Player();
                p.setNickname(rs.getString("nickname"));
                players.add(p);
            }
        } catch (SQLException e) {

        }

        return players;
    }
}
