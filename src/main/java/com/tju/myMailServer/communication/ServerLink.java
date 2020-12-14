package com.tju.myMailServer.communication;

import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.server.MailServer;
import com.tju.myMailServer.utils.PacketParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerLink extends Thread {
    int port;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream out = null;
    DataInputStream in = null;

    public ServerLink(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while(true){
            try {
                System.out.println("Server: Ready~~");

                socket = serverSocket.accept();
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                String[] strReceived = in.readUTF().split("#");
                int requestCode = Integer.parseInt(strReceived[0]);
                String content = strReceived[1];
                MailServer mailServer=new MailServer();
                String result="1234";
                System.out.println(Arrays.toString(strReceived));
                switch (requestCode){
                    case -1:
                        result=mailServer.onSendRequest(content);
                        break;
                    case 0:
                        result=mailServer.onReceiveRequest(content);
                        break;
                    case 1:
                        result=mailServer.onUnreadRequest(content);
                        break;
                    case 2:
                        result=mailServer.onSentRequest(content);
                        break;
                    case 3:
                        result=mailServer.onScriptRequest(content);
                        break;
                    case 5:
                        result=mailServer.onDeleteRequest(content);
                }
                System.out.println(result);
                out.writeUTF(result);
                out.close();
                in.close();
                socket.close();
            }catch (IOException e) {
                e.printStackTrace();
                interrupt();
            }

        }
    }
}
