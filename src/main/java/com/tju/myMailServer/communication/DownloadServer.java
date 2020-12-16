package com.tju.myMailServer.communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DownloadServer extends Thread {

    int port;
    ServerSocket serverSocket;
    final String dir = "D:\\uploaded";

    public DownloadServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket s = serverSocket.accept();
                OutputStream out = s.getOutputStream();
                byte[] b = new byte[1024];
                String filePath = new String(b, StandardCharsets.UTF_8);
                InputStream is = new FileInputStream(new File(dir, filePath));
                int i = is.read(b);
                while (i > 0) {
                    out.write(b);
                    i = is.read(b);
                }
                is.close();
                out.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
