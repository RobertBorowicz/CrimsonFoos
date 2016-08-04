public class ReccommendedMatchupService
{
  public List<Matchup> getReccommendedMatchups(List<Player> players)
  {
    //todo:  get reccommended matchup from DB logic
    //todo:  assign players based on that
    int G = 100; // total number of all games for all players
    int g = 0; // player one's games
    int p = 0; // player two'g games
    int t = 0; // number of times player1 and player2 have played together
    int T = 0; // number of times player1 and player2 SHOULD have played

    int[][] matrix = new int[players.size()][players.size()]

    for(int i = 0; i < players.size(); i ++)
    {
      for(int j = 0; j < players.size(); j ++)
      {
        if (i == j)
        {
          int[i][j] = Integer.MIN_VALUE;
        }
        else
        {
          int ci = 0;

          g = players.get(i).getGamesPlayed();
          p = players.get(j).getGamesPlayed();
          t = players.get(i).getGamesPlayedWith(players.get(j));

          if (G-g != 0)
          {
            T = Math.ceil(((double) p / (G - g)) * g)
            ci = t - T;
          }
          matrix[i][j] = ci;
        }
      }
    }

    int highestCi = matrix[0][1];

    int p1index = 0;
    int p2index = 0;

    for(int i = 0; i < matrix.length; i ++)
    {
      for(int j = 0; j < matrix[i].length; j ++)
      {
        if (i != j)
        {
          if (matrix[i][j] > highestCi)
          {
            highestCi = matrix[i][j];
            p1index = i;
            p2index = j;
          }
        }
      }
    }

    Team redTeam = new Team();
    redTeam.setColor("RED");
    redTeam.setPlayer1(players.get(p1index));
    redTeam.setPlayer2(players.get(p2index));

    highestCi = matrix[0][1];

    int p3index = 0;
    int p4index = 0;

    for(int i = 0; i < matrix.length; i ++)
    {
      for(int j = 0; j < matrix[i].length; j ++)
      {
        if (i != j && i != p1 && i != p2 && j != p1 && j != p2)
        {
          if (matrix[i][j] > highestCi)
          {
            highestCi = matrix[i][j];
            p3index = i;
            p4index = j;
          }
        }
      }
    }

    Team blueTeam = new Team();
    blueTeam.setColor("BLUE");
    blueTeam.setPlayer1(players.get(p3index));
    blueTeam.setPlayer2(players.get(p4index));

    Matchup match = new Matchup(redTeam, blueTeam);

    return new ArrayList(); //todo: stuff
  }

}