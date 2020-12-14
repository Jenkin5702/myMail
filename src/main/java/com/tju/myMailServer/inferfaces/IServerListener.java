package com.tju.myMailServer.inferfaces;

import com.tju.myMailServer.entities.Mail;

public interface IServerListener {
    public String onSendRequest(String mailJson);
    public String onReceiveRequest(String address);
    public String onScriptRequest(String address);
    public String onSentRequest(String address);
    public String onUnreadRequest(String address);
    public String onDeleteRequest(String mailJSON);
    public String onReadRequest(String mailJSON);
}
