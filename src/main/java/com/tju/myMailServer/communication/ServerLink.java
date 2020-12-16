package com.tju.myMailServer.communication;

import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.server.MailServer;
import com.tju.myMailServer.utils.PacketParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerLink extends Thread {
    private final int SEND=-1;
    private final int RECEIVED=0;
    private final int UNREAD=1;
    private final int SENT=2;
    private final int SCRIPT=3;
    private final int READ=4;
    private final int DELETE=5;
    private final int UPLOAD=6;
    private final int DOWNLOAD=7;

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
                socket = serverSocket.accept();
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                String[] strReceived = in.readUTF().split("#");
                int requestCode = Integer.parseInt(strReceived[0]);
                String content = strReceived[1];
                MailServer mailServer=new MailServer();
                String request="";
                String response="";

                switch (requestCode){
                    case SEND:
                        request="[Send] ";
                        response=mailServer.onSendRequest(content);
                        break;
                    case RECEIVED:
                        request="[List Received] ";
                        response=mailServer.onReceiveRequest(content);
                        break;
                    case UNREAD:
                        request="[List Unread] ";
                        response=mailServer.onUnreadRequest(content);
                        break;
                    case SENT:
                        request="[List Sent] ";
                        response=mailServer.onSentRequest(content);
                        break;
                    case SCRIPT:
                        request="[List Script] ";
                        response=mailServer.onScriptRequest(content);
                        break;
                    case READ:
                        request="[Read] ";
                        response=mailServer.onReadRequest(content);
                        break;
                    case DELETE:
                        request="[Delete] ";
                        response=mailServer.onDeleteRequest(content);
                        break;
                    case DOWNLOAD:
                        request="[DOWNLOAD]";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(content)));
                        PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        String s = reader.readLine();
                        while (s != null) {
                            out.println(s);
                            s = reader.readLine();
                        }
                        reader.close();
                        out.close();
                        break;
                    case UPLOAD:
                        request="[UPLOAD]";
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(content)));
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String x = in.readLine();
                        while (x != null) {
                            writer.write(x + "\n");
                            writer.flush();
                            x = in.readLine();
                        }
                        writer.close();
                        in.close();
                        break;

                }
                System.out.println(request+content);
                System.out.println("{Response} "+response);
                out.writeUTF(response);
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
