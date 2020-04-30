import java.util.ArrayList;
import java.util.List;

public class Game {
    public char name;
    private Board board;
    private List<Player> players = new ArrayList<>();

    /*  public String viewBoard() {
          return board.viewMarix();
      }
  */
    public void addPlayer(Player player) {
        players.add(player);
    }

    public Game(char name) {
        this.name = name;
        board = new Board();
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
