package rome.services;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import rome.model.base.Player;
import rome.model.base.Team;
import rome.model.base.Matchup;
import rome.services.RecommendedMatchupService;

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
        player1.addGame(match);
        player2.addGame(match);
        player3.addGame(match);
        player4.addGame(match);
      }

      List<Player> players = new ArrayList<>();
      players.add(player1);
      players.add(player2);
      players.add(player3);
      players.add(player4);

      RecommendedMatchupService service = new RecommendedMatchupService();
      List<Matchup> recommendedGames = service.getRecommendedMatchups(players);

      assertNotNull(recommendedGames);
      assertTrue(!recommendedGames.isEmpty());
    }

}