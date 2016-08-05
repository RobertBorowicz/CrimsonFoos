package rome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rome.model.base.Bulk;
import rome.model.base.Game;
import rome.model.base.Player;
import rome.model.base.Team;
import rome.services.GameService;
import rome.services.PlayerService;
import rome.services.TeamService;

import java.util.List;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 8/3/2016
 *
 */
@Controller
public class BulkController {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    TeamService teamService;

    /**
     * Retrieve all players.
     * @return a list of all players and the status code
     */
    @RequestMapping(value = "/api/bulk/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bulk> getAllPlayers() {

        List<Player> players = playerService.getAllPlayers();
        List<Game> games = gameService.getAllGames();
        List<Team> teams = teamService.getAllTeams();

        if (players.isEmpty() && games.isEmpty() && teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Bulk bulk = new Bulk(players, games, teams);

        return new ResponseEntity<>(bulk, HttpStatus.OK);
    }
}
