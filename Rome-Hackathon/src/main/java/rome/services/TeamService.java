package rome.services;

import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rome.database.TeamsDAO;
import rome.model.base.Team;

/**
 * Created by walkercr on 8/5/16.
 */
public class TeamService {

    /**
     * Retrieves a list of all teams.
     * @return a list of all teams
     */
    public List<Team> getAllTeams() {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        List<Team> teams;

        TeamsDAO conn = (TeamsDAO) context.getBean("teamsDAO");

        teams = conn.getTeams();

        return teams;
    }
}
