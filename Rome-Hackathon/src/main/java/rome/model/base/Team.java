package rome.model.base;

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