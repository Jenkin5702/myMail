package com.tju.myMailServer.communication;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class UploadServer extends Thread{

    int port;
    ServerSocket serverSocket;
    final String dir = "D:\\uploaded";

    public UploadServer(int port) throws IOException {
        this.port=port;
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket s = serverSocket.accept();
                InputStream inputStream = s.getInputStream();
                byte[] b = new byte[1024];
                int i = inputStream.read(b);
                String fileName=new String(b, StandardCharsets.UTF_8);
                File file=new File(dir, fileName);
                System.out.println(file.getPath());
                OutputStream outputStream = new FileOutputStream(file);

                i = inputStream.read(b);
                while (i > 0) {
                    outputStream.write(b);
                    outputStream.flush();
                    i = inputStream.read(b);
                }
                outputStream.close();
                inputStream.close();
                s.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5432);

    }
}
