package rome.database;

import javax.sql.DataSource;
import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;
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
