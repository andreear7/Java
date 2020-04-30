import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    public static final int PORT = 8100;
    private Boolean stop = false;
    private ServerSocket serverSocket;
    private List<Game> games = new ArrayList<>();

    public Game findGame(char name) {
        for (Game game : games) {
            if (game.getName() == name) {
                return game;
            }
        }
        return null;
    }

    public void createGame(char gameName) {
        Game game = new Game(gameName);
        games.add(game);
        Player player = new Player(1);
        game.addPlayer(player);
    }

    public void joinGame(char name) {
        for (Game game : games) {
            if (game.getName() == name) {
                Player player = new Player(2);
                game.addPlayer(player);
            }
        }

    }

    public int submitMove(Game game, int type, int pozI, int pozY) {
        System.out.println(game);
        System.out.println(pozI);
        System.out.println(pozY);
        System.out.println(type);
        if (game.getBoard().move(pozI, pozY, type) == 1) {
            return 1;
        } else {
            return 0;
        }
        // game.viewBoard();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (!stop) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println("A venit un client!");
                new ClientThread(socket, this).start();
            }

        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
    }
}
