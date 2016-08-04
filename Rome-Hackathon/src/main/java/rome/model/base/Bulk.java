package rome.model.base;

import java.util.List;

/**
 * The Bulk data model.
 *
 * @author Rome
 * @version 1.0
 * @since 8/3/2016
 *
 */
public class Bulk {

    private List<Player> players;
    private List<Game> games;

    public Bulk(List<Player> players, List<Game> games) {
        this.players = players;
        this.games = games;
    }
}
