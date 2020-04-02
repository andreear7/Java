import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab7PA {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        List<Token> tokens = new ArrayList<>();
        for (int index = 1; index <= n; index++) {
            //Random rand = new Random();
           // int number = rand.nextInt(m - 1) + 1;
            Token token = new Token(index);
            tokens.add(token);
        }
        Board board = new Board(tokens);
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, board);
        game.startThreads();
    }
}
