import java.util.*;
import static java.lang.Thread.sleep;

public class Game {
    final List<Player> players;
    Board board;
    private boolean available = true;
    int winner;
    boolean gameOver = false;

    public Game(List<Player> players, Board board) {
        this.players = players;
        for (int index = 0; index < players.size(); index++) {
            players.get(index).setGame(this);
        }
        this.board = board;
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

    void startThreads() {
        int index;
        for (index = 0; index < players.size(); index++) {
            Runnable runnable = players.get(index);
            new Thread(runnable).start();
        }

        while (true) {
            if (gameOver) {
                printWinner();
                break;
            } else {
                try {
                    sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void getWinner(Player player) {
        Collections.sort(player.getExtractedTokens(), Comparator.comparing(Token::getNumber).reversed());
        System.out.println(player.getName() + " a luat " + player.getExtractedTokens());
        getMaxProgression();
    }

    void getMaxProgression() {
        List<Integer> maxime = new ArrayList<>();
        int maxim = 0;
        for (Player player : players) {
            int index;
            List<Integer> difference = new ArrayList<>();
            for (index = 0; index < player.extractedTokens.size() - 1; index++) {
                difference.add(player.extractedTokens.get(index).getNumber() - player.extractedTokens.get(index + 1).getNumber());
            }
            int k = 0;
            for (index = 0; index < difference.size() - 1; index++) {
                if (difference.get(index) == difference.get(index + 1))
                    k++;
                if (k > maxim) {
                    maxim = k;
                }
            }
            maxime.add(maxim);
        }
        maxim = 0;
        for (int index = 0; index < maxime.size(); index++) {

            if (maxime.get(index) > maxim) {
                maxim = maxime.get(index);
                winner = index;
            }
        }
        gameOver = true;
    }
    void printWinner() {
        System.out.println(players.get(winner).getName() + " a castigat!");
    }
}
