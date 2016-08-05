package rome.services;

import java.util.List;
import org.junit.Test;
import rome.model.base.Team;

import static org.junit.Assert.*;

/**
 * Created by walkercr on 8/5/16.
 */
public class TeamServiceTest {
    @Test
    public void getAllTeams() throws Exception {
        TeamService service = new TeamService();
        List<Team> teams = service.getAllTeams();
        for (Team team : teams) {
            System.out.println(team);
        }
    }

}