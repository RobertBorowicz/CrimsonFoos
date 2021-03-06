package rome.model.base;

/**
 * Created by borowicr on 8/4/16.
 */
public class Game {

    private Team winner = new Team();
    private Team loser = new Team();
    private String date = new String();
    private String winColor = new String();
    private int gameID;

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getLoser() {
        return loser;
    }

    public void setLoser(Team loser) {
        this.loser = loser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWinColor() {
        return winColor;
    }

    public void setWinColor(String winColor) {
        this.winColor = winColor;
    }

    public String toString() {
        String toRet = "";

        toRet += "Game: " + gameID + "\n";
        toRet += "Winning side: " + winColor + "\n";
        toRet += "Winners: " + winner.toString() + "\n";
        toRet += "Losers: " + loser.toString() + "\n";

        return toRet;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
