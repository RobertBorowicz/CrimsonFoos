package rome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rome.model.base.Bulk;
import rome.model.base.Game;
import rome.model.base.Player;
import rome.services.GameService;
import rome.services.PlayerService;

import java.util.List;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 8/3/2016
 *
 */
public class BulkController {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    /**
     * Retrieve all players.
     * @return a list of all players and the status code
     */
    @RequestMapping(value = "/api/bulk/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bulk> getAllPlayers() {

        List<Player> players = playerService.getAllPlayers();
        List<Game> games = gameService.getAllGames();

        if (players.isEmpty() && games.isEmpty()) {
            return new ResponseEntity<Bulk>(HttpStatus.NO_CONTENT);
        }

        Bulk bulk = new Bulk(players, games);

        return new ResponseEntity<Bulk>(bulk, HttpStatus.OK);
    }
}
