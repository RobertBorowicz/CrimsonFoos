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
        System.out.println("I'm at the players controller");
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            return new ResponseEntity<List<Player>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
    }

    /**
     * Retrieve a single player.o
     * @param id the id of the player t be retrieved
     * @return the player with the given id and the status code
     */
    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") long id) {
        //Player player = playerService.getPlayerById(id);
        Player player = new Player();
        player.setId(1);
        player.setFirstName("Craig");
        player.setLastName("Walker");
        player.setNickname("cw");
        if (player == null) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }

    /**
     * Create a player.
     * @param player the player to be created
     * @param builder the uri builder to be utilized
     * @return http headers and the status code
     */
    @RequestMapping(value = "/api/player/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPlayer(@RequestBody Player player, UriComponentsBuilder builder) {

        if (playerService.exists(player)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        playerService.createPlayer(player);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/player/{id}").buildAndExpand(player.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Update a player.
     * @param id the id of the player to be updated
     * @return the update player and the status code
     */
    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Player> updatePlayerById(@PathVariable("id") long id, @RequestBody Player player) {
        Player currentPlayer = playerService.getPlayerById(id);
        if (currentPlayer == null) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }

        currentPlayer.setFirstName(player.getFirstName());
        currentPlayer.setLastName(player.getLastName());
        currentPlayer.setNickname(player.getNickname());

        playerService.updatePlayer(currentPlayer);
        return new ResponseEntity<Player>(currentPlayer, HttpStatus.OK);
    }

    /**
     * Delete all players.
     * @return the status code
     */
    @RequestMapping(value = "/api/player/", method = RequestMethod.DELETE)
    public ResponseEntity<Player> deleteAllPlayers() {
        playerService.deleteAllPlayers();
        return new ResponseEntity<Player>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete a player.
     * @return the status code
     */
    @RequestMapping(value = "/api/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> deletePlayerById(@PathVariable("id") long id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }

        playerService.deletePlayerById(id);
        return new ResponseEntity<Player>(HttpStatus.NO_CONTENT);
    }
}
