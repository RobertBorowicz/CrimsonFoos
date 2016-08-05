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

import rome.model.base.Player;
import rome.services.PlayerService;

/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 */
@RestController
public class PlayerController {

    @Autowired                    // Configure PlayerService bean in applicationContext.xml
    PlayerService playerService;  // Service which will do all data retrieval/manipulation work

    /**
     * Retrieve all players.
     * @return a list of all players and the status code
     */
    @RequestMapping(value = "/api/player/", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> getAllPlayers() {

        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            return new ResponseEntity<List<Player>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
    }

    /**
     * Create a player.
     * @param player the player to be created
     * @return http headers and the status code
     */
    @RequestMapping(value = "/api/player/", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {

        Player p = playerService.createPlayer(player);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    /**
     * Update a player.
     * @param id the id of the player to be updated
     * @return the update player and the status code
     */
    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePlayerById(@PathVariable("id") long id, @RequestBody Player player) {

        if (!playerService.updatePlayer(id, player)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete a player by id.
     * @param id the id of the player to be deleted
     * @return the status code
     */
    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlayerById(@PathVariable("id") long id) {

        if (!playerService.deletePlayerById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
