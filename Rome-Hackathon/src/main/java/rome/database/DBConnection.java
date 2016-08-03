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

    public JdbcTemplate getConnection() throws SQLException {
        //Driver myDriver = new oracle.jdbc.driver.OracleDriver();
        //DriverManager.registerDriver( myDriver );
        //Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/CrimsonFoosball?autoReconnect=true&useSSL=false", "root", "crimsonFoos");
        //System.out.println("Connected to database");
        //return conn;
        JdbcTemplate conn = new JdbcTemplate();
        return conn;

    }



}
