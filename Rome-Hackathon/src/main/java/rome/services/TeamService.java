package rome.services;

import java.util.List;
import rome.database.DBConnection;
import rome.model.base.Team;

/**
 * Created by walkercr on 8/5/16.
 */
public class TeamService {

    public List<Team> getAllTeams() {
        DBConnection db = new DBConnection();
        List<Team> teams = db.getAllTeams();
        db.close();
        return teams;
    }
}
