package rome.database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by borowicr on 8/4/16.
 */
public class DBConnectionTest {
    @Test
    public void close() throws Exception {
        DBConnection db = new DBConnection();
        db.close();
    }

}