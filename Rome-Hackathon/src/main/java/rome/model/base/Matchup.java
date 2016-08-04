public class Matchup
{
  private Team team1;
  private Team team2;
  private double avgCI;

  public Matchup(Team team1, Team team2, double calcedCI;
  {
    this.team1 = team1;
    this.team2 = team2;
  }

  public Team getTeam1()
  {
    return team1;
  }
  public Team getTeam2()
  {
    return team2;
  }

  public void setTeam1(Team team1)
  {
    this.team1 = team1;
  }
  public void setTeam2(Team team2)
  {
    this.team2 = team2;
  }

  public boolean hasPlayer(Player player)
  {
    if(team1.hasPlayer(player) || team2.hasPlayer(player))
    {
      return true;
    }
    return false;
  }

  public boolean equals(Matchup otherMatchup)
  {
    boolean hasFirstTeam = otherMatchup.getTeam1().equals(team1) || otherMatchup.getTeam1().equals(team2);
    boolean hasSecondTeam = otherMatchup.getTeam2().equals(team1) || otherMatchup.getTeam2().equals(team2);
    return hasFirstTeam && hasSecondTeam;
  }
}