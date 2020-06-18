import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] agrs) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30000);

        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("demo".getBytes());
        System.out.println(socket);
        outputStream.close();


    }
}
