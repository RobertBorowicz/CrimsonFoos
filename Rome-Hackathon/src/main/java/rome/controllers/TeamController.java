package rome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rome.model.base.Team;
import rome.services.TeamService;

import java.util.List;

/**
 * Created by walkercr on 8/5/16.
 */
@RestController
public class TeamController {

    @Autowired                    // Configure PlayerService bean in applicationContext.xml
    TeamService teamService;      // Service which will do all data retrieval/manipulation work

    /**
     * Retrieve all players.
     *
     * @return a list of all players and the status code
     */
    @RequestMapping(value = "/api/team/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Team>> getAllGames() {

        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
    }
}
