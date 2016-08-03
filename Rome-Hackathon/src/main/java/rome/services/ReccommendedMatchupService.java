public class ReccommendedMatchupService
{
  public List<Matchup> getReccommendedMatchups(List<Player> players)
  {
    //todo:  get reccommended matchup from DB logic
    //todo:  assign players based on that
    int G = 100; // total number of all games for all players
    int g = 10; // player one's games
    int p = 10; // player two'g games
    int t = 5; // number of times player1 and player2 have played together
    int T = 2; // number of times player1 and player2 SHOULD have played

    int[][] matrix = new int[players.size()][players.size()]

    for(int i = 0; i < players.size(); i ++)
    {
      for(int j = 0; j < players.size(); j ++)
      {
        if (i == j)
        {
          int[i][j] = Math.MIN_INT;
        }
        else
        {
          g = 0; //playerA's games
          p = 0; //playerB's games
          int[i][j] =
        }
      }
    }

    return new ArrayList(); //todo: stuff
  }
}