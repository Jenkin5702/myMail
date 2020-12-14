package com.tju.myMailServer.server;

import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.inferfaces.IServerListener;
import com.tju.myMailServer.utils.PacketParser;

import java.util.HashMap;
import java.util.Map;

public class MailServer implements IServerListener {
    DBAgent dbAgent=new DBAgent("mail");

    @Override
    public String onSendRequest(String mailJson) {
        return dbAgent.insert(mailJson);
    }

    /**
     * Get mail data of received.
     * @return Packet containing mail data, in JSON format.
     */
    @Override
    public String onReceiveRequest(String address) {
        HashMap<String, Object> kv=new HashMap<>();
        kv.put("to", address);
        kv.put("status", "SENT");
        return dbAgent.load(kv);
    }

    /**
     * Get mail data of script.
     * @return Packet containing mail data, in JSON format.
     */
    @Override
    public String onScriptRequest(String address) {
        HashMap<String, Object> kv=new HashMap<>();
        kv.put("from", address);
        kv.put("status", "SCRIPT");
        return dbAgent.load(kv);
    }

    @Override
    public String onSentRequest(String address) {
        HashMap<String, Object> kv=new HashMap<>();
        kv.put("from", address);
        kv.put("status", "SENT");
        return dbAgent.load(kv);
    }

    /**
     * Get mail data of unread.
     * @return Packet containing mail data, in JSON format.
     */
    @Override
    public String onUnreadRequest(String address) {
        HashMap<String, Object> kv=new HashMap<>();
        kv.put("to", address);
        kv.put("status", "UNREAD");
        return dbAgent.load(kv);
    }

    /**
     * Get mail data of deleted.
     * @return Packet containing mail data, in JSON format.
     */
    @Override
    public String onDeleteRequest(String mailJson) {
        Mail mail= PacketParser.parsePacket(mailJson);
        HashMap<String, Object> kv=new HashMap<>();
        kv.put("to", mail.getTo());
        kv.put("from", mail.getFrom());
        kv.put("time", mail.getTime());
        kv.put("title", mail.getTitle());
        return dbAgent.delete(kv);
    }
}
