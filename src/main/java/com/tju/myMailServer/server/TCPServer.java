package com.tju.myMailServer.server;

import com.tju.myMailServer.communication.ServerLink;

import java.io.*;

public class TCPServer {
    public TCPServer(int port) throws IOException {
        ServerLink server = new ServerLink(port);
        server.start();
    }
}

