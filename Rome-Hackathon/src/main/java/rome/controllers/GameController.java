package rome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rome.model.base.Game;
import rome.services.GameService;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 */
@RestController
public class GameController {

    @Autowired                    // Configure PlayerService bean in applicationContext.xml
    GameService gameService;  // Service which will do all data retrieval/manipulation work

    /**
     * Retrieve all players.
     *
     * @return a list of all players and the status code
     */
    @RequestMapping(value = "/api/game/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> getAllGames() {

        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
}
