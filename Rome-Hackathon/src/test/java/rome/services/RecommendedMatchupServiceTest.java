package rome.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecommendedMatchupServiceTest {
    @Test
    public void getRecommendedMatchups() throws Exception
    {
      Player player1 = new Player(1, "Player", "One", "P1");
      Player player2 = new Player(2, "Player", "Two", "P2");
      Player player3 = new Player(3, "Player", "Three", "P3");
      Player player4 = new Player(1, "Player", "Four", "P4");

      Team oneTwo = new Team(player1, player2, 0);
      Team oneThree = new Team(player1, player3, 0);
      Team oneFour = new Team(player1, player4, 0);
      Team twoThree = new Team(player2, player3, 0);
      Team twoFour = new Team(player2, player4, 0);
      Team threeFour = new Team(player3, player4, 0);

      List<Matchup> pastGames = new ArrayList<>();

      Matchup matchOne = new Matchup(oneTwo, threeFour, 0);
      Matchup matchTwo = new Matchup(oneThree, twoFour, 0);
      Matchup matchThree = new Matchup(oneFour, twoThree, 0);
      Matchup matchFour = new Matchup(oneTwo, threeFour, 0);
      Matchup matchFive = new Matchup(oneThree, twoFour, 0);

      pastGames.add(matchOne);
      pastGames.add(matchTwo);
      pastGames.add(matchThree);
      pastGames.add(matchFour);
      pastGames.add(matchFive);

      for (Matchup match :
          pastGames)
      {
        player1.add(match);
        player2.add(match);
        player3.add(match);
        player4.add(match);
      }

      List<Player> players = new ArrayList<>();
      players.add(player1);
      players.add(player2);
      players.add(player3);
      players.add(player5);

      List<Matchups> recommendedGames = ReccommendedMatchupService.getRecommendedMatchups(players);

      assert(!recommendedGames == null && !recommendedGames.empty())
    }

}