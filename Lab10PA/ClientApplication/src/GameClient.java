import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    static String serverAddress = "127.0.0.1";
    static int PORT = 8100;
    Socket socket = null;

    public GameClient() throws IOException {
        this.socket = new Socket(serverAddress, PORT);
    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void sendRequestToServer(String request) throws IOException {
        OutputStreamWriter output = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(output);
        out.write(request + "\n");
        out.flush();
    }

    private String receiveResponseFromServer() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
        return response;
    }

    public static void main(String[] args) {
        try {
            GameClient gameClient = new GameClient();
            System.out.println("Bine ai venit! Pentru a crea un joc tasteaza create game urmat de id-ul jocului format dintr-un caracter,pentru a intra in joc tasteaza join game urmat de id-ul jocului,iar pentru a muta tasteaza submit move urmat de numele jocului,tipul tau si coordonatele: x si y.");
            while (true) {
                String request = gameClient.readFromKeyboard();

                gameClient.sendRequestToServer(request);
                String response = gameClient.receiveResponseFromServer();
                System.out.println(response);
                if (response.equals("Ma opresc") || response.equals("Serverul s-a oprit intre timp!") || request.equals("exit")) {
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
