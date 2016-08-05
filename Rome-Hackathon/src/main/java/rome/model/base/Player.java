package rome.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player.
 *
 * @author Rome
 * @version 1.0
 * @since 7/27/2016
 *
 */
public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String nickname;
    public List<Matchup> matchupList = new ArrayList<>();
    private Stats stats;

    public Player() {}

    public Player(int id, String firstName, String lastName, String nickname) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        matchupList = new ArrayList<>();
        stats = new Stats();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean equals(Player p) {
        return (this.id == p.id)
                && (this.firstName.equals(p.firstName))
                && (this.lastName.equals(p.lastName))
                && (this.nickname.equals(p.nickname));
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nFirst Name: " + firstName
                + "\nLast Name: " + lastName
                + "\nNickname: " + nickname
                + "\nStats: " + stats.toString();
    }

    public int getGamesPlayed()
    {
        return matchupList.size();
    }

    public int getGamesPlayedWith(Player player)
    {
        int games = 0;
        for (Matchup match : matchupList)
        {
            if (match.hasPlayer(player))
            {
                games++;
            }
        }
        return games;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

}
