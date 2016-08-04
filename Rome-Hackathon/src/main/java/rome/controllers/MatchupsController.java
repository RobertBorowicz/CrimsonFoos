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

import rome.model.base.Matchup;
import rome.model.base.Player;
import rome.services.RecommendedMatchupService;


/**
 * The Player controller.
 *
 * @author Rome
 * @version 1.0
 * @since 8/3/2016
 *
 */
@RestController
public class MatchupsController {

    //@Autowired
    RecommendedMatchupService matchupService;

    /**
     * Retrieve all players.
     * @param players the list of players to find the best matchups for
     * @return a sorted list of the best matchups for the given players
     */
    @RequestMapping(value = "/api/matchups/", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Matchup>> getMatchups(List<Player> players) {
        // matchupService.getRecommendedMatchups(players)
        return null;
    }
}
