import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    String name;
    volatile Game game;
    private boolean available = false;

    public List<Token> getExtractedTokens() {
        return extractedTokens;
    }
    List<Token> extractedTokens = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public void run() {

        while (!game.board.tokenList.isEmpty() || (game.gameOver)) {
            extractToken();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        game.getWinner(this);
    }

    public synchronized void extractToken() {
        Random rand = new Random();
        int position = rand.nextInt(game.board.tokenList.size());
        Token token = game.board.tokenList.get(position);
        System.out.println("Playerul " + name + " a luat " + token);
        extractedTokens.add(token);
        game.board.removeToken(token);
    }

    //aici sunt incercari pentru optional care nu au iesit
    /*
public void run() {
    int number = game.players.indexOf(this);
    int nextPlayer;
    if (number == 1) {
        nextPlayer = 0;
    } else {
        nextPlayer = 1;
    }
    while (!game.board.tokenList.isEmpty()) {
        if (!game.players.get(nextPlayer).getActive()) {
            active = true;
            extractToken();

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        else
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //game.getWinner(this);
    }
}
*/


    /*
    public void run()
    {
        extractToken();
    }
        public synchronized void extractToken() {
            while (true) {
                if (!active) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                active = false;
                notifyAll();
                if (!game.board.tokenList.isEmpty()) {
                    Random rand = new Random();
                    int position = rand.nextInt(game.board.tokenList.size());
                    Token token=game.board.tokenList.get(position);
                    System.out.println("Playerul " + name + " a luat " + token);
                    extractedTokens.add(token);
                    game.board.removeToken(token);
                    active = true;
                    notifyAll();
                } else {
                    break;
                }
            }
            game.getWinner(this);
        }
    */
    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
