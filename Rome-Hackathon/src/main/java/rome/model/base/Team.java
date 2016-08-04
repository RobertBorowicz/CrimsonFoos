package rome.model.base;

<<<<<<< HEAD
public class Team
{
  private String color;
  private Player player1;
  private Player player2;
  private double avgCI;

  public Team(){}
  public Team(Player p1, Player p2, double calcedCI)
  {
    player1 = p1;
    player2 = p2;
    avgCI = avgCI;
  }

  public Player getPlayer1()
  {
    return player1;
  }
  public Player getPlayer2()
  {
    return player2;
  }
  public String getColor()
  {
    return this.color;
  }
  public double getCI()
  {
    return avgCI;
  }

  public void setPlayer1(Player player1)
  {
    this.player1 = player1;
  }
  public void setPlayer2(Player player2)
  {
    this.player2 = player2;
  }
  public void setColor(String newColor)
  {
    if (newColor.equalsIgnoreCase("RED") || newColor.equalsIgnoreCase("BLUE"))
    {
      this.color = newColor.toUpperCase();
    }
  }

  public boolean hasPlayer(Player player)
  {
    return player1.equals(player) || player2.equals(player);
  }

  public boolean equals(Team otherTeam)
  {
    boolean hasPlayer1 = player1.equals(otherTeam.getPlayer1()) || player1.equals(otherTeam.getPlayer2());
    boolean hasPlayer2 = player2.equals(otherTeam.getPlayer1()) || player2.equals(otherTeam.getPlayer2());
    return hasPlayer1 && hasPlayer2;
  }
}
=======
import java.util.ArrayList;

/**
 * Created by borowicr on 8/4/16.
 */
public class Team {

    private ArrayList<Player> players = new ArrayList<>();
    private int score;
    private int teamID;

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

    public String toString() {
        String toRet = "";

        toRet += "\nPlayer 1: " + players.get(0).toString() + "\n";
        toRet += "Player 2: " + players.get(1).toString() + "\n";
        toRet += "Score: " + score;

        return toRet;
    }
}
>>>>>>> Database-Setup
