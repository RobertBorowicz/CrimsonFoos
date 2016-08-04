package rome.model.base;

import java.util.ArrayList;

/**
 * Created by borowicr on 8/4/16.
 */
public class Team {

    private ArrayList<Player> players = new ArrayList<>();
    private int score;
    private int teamID;
    private double avgCI;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public double getCI()
    {
        return avgCI;
    }

    public void setCI(double ci) {this.avgCI = ci;}

    public boolean equals(Team otherTeam)
    {
        return this.teamID == otherTeam.getTeamID();
    }

    public boolean hasPlayer(Player player)
    {
        return players.contains(player);
    }

    public String toString() {
        String toRet = "";

        toRet += "\nPlayer 1: " + players.get(0).toString() + "\n";
        toRet += "Player 2: " + players.get(1).toString() + "\n";
        toRet += "Score: " + score;

        return toRet;
    }
}
