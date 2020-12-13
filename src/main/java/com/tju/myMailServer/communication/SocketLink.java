package com.tju.myMailServer.communication;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketLink {
    private static final String ip = "127.0.0.1";
    private static final int port = 5575;

    public static String request(String packet){
        try{
            Socket socket = new Socket(ip, port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeUTF(packet);
            String strReceived = in.readUTF();
            out.close();
            socket.close();
            return strReceived;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean post(String packet){
        try{
            Socket socket = new Socket(ip, port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(packet);
            out.close();
            socket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
