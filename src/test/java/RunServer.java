import com.tju.myMailServer.server.TCPServer;

import java.io.IOException;

public class RunServer {

    public static void main(String[] args)throws IOException {
        new TCPServer(5575);
        new TCPServer(5432);
    }
}
