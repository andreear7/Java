import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    private final GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            while (true) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                String raspuns;

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                if (server.getStop()) {
                    raspuns = "Serverul s-a oprit intre timp!";
                    out.println(raspuns);
                    out.flush();
                    socket.close();
                    break;
                }
                if (request.equals("stop")) {
                    server.setStop(true);
                    raspuns = "Ma opresc";
                    out.println(raspuns);
                    out.flush();
                    System.exit(0);
                    //socket.close();
                    //break;
                } else {
                    if (request.equals("exit")) {
                        raspuns = "Ai iesit!";
                        out.println(raspuns);
                        out.flush();
                        socket.close();
                        break;
                    } else {
                        raspuns = " ";
                        //switch (request) {
                        if (request.contains("create game")) {
                            //  char[] req = new char[0];
                            //request.getChars(13, 15, req, 0);
                            System.out.println(request.charAt(12));
                            server.createGame(request.charAt(12));
                            raspuns = "Joc creat! Joci cu numarul 1!";
                        }
                        if (request.contains("join game")) {
                            //char[] reqq = new char[0];
                            //request.getChars(12, 14, reqq, 0);
                            System.out.println(request.charAt(10));
                            server.joinGame(request.charAt(10));
                            raspuns = "Ai intrat in joc! Joci cu numarul 2!";
                        }
                        if (request.contains("submit move")) {
                            //char[] reqqq = new char[0];
                            //request.getChars(13, 15, reqqq, 0);
                            System.out.println(request);
                            System.out.println(request.charAt(12));
                            System.out.println(request.charAt(14));
                            System.out.println(request.charAt(16));
                            System.out.println(request.charAt(18));
                            Game game = server.findGame(request.charAt(12));
                            if (server.submitMove(game, request.charAt(14) - 48, request.charAt(16) - 48, request.charAt(18) - 48) == 1) {
                                raspuns = game.getBoard().viewMarix();
                            } else {
                                raspuns = "Nu poti muta in aceasta casuta!Este deja o piesa acolo!";
                            }
                        }


                        //   raspuns = "Fac ceva " + request;
                        System.out.println(raspuns);
                        out.println(raspuns);
                        out.flush();
                    }
                }
            }
            socket.close();
        } catch (IOException e) {

            System.err.println("Communication error... " + e);
        }
    }
}
