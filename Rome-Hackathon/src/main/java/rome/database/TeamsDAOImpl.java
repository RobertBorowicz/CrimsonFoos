package rome.database;

import rome.model.base.Team;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by walkercr on 8/5/16.
 */
public class TeamsDAOImpl implements TeamsDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Team> getTeams() {
        List<Team> teams = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM teams");

            while (rs.next()) {
                // TODO: Implement sql query and create a list of teams
            }
        } catch (SQLException e) {

        }

        return teams;
    }
}
