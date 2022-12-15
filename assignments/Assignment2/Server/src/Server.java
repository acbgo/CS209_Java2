import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket myServerSocket;

    public Server() {
        startConnection();
    }

    public void startConnection() {
        try {
            myServerSocket = new ServerSocket(8088);
            System.out.println("server started: <http://127.0.0.1:8088>\n");

            new Thread(() -> {
                try {
                    while (true){
                        Socket socket = myServerSocket.accept();
                        new ServerHandler(socket);
                    }
                } catch (Exception e){
                    try {
                        myServerSocket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
