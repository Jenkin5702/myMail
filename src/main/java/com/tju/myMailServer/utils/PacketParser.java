package com.tju.myMailServer.utils;

import com.alibaba.fastjson.JSON;
import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.inferfaces.IPacketParser;

import java.util.List;

public class PacketParser {
    private static final int SENT=MailTypes.SENT.getCode();
    private static final int UNREAD=MailTypes.UNREAD.getCode();
    private static final int RECEIVED=MailTypes.RECEIVED.getCode();
    private static final int SCRIPT=MailTypes.SCRIPT.getCode();
    private static final int READ=MailTypes.READ.getCode();
    private static final int DELETE=MailTypes.TO_DELETE.getCode();


    public static Mail parsePacket(String mailPacket) {
        return JSON.parseObject(mailPacket, Mail.class);
    }

    public static List<Mail> parsePacketList(String mailPacket) {
        return JSON.parseArray(mailPacket, Mail.class);
    }

    public static String packetSend(Mail mail){
        return "-1#" + mail.toString();
    }

    public static String packetSaveScript(Mail mail){
        return "-1#" + mail.toString();
    }

    public static String packetDeleteMail(Mail mail){
        return DELETE + "#" + mail.toString();
    }

    public static String packetMarkRead(Mail mail){
        return READ + "#" + mail.toString();
    }

    public static String packetRequestSent(String address){
        return SENT + "#" +address;
    }

    public static String packetRequestScript(String address){
        return SCRIPT + "#" +address;
    }

    public static String packetRequestReceived(String address){
        return RECEIVED + "#" +address;
    }

    public static String packetRequestUnread(String address){
        return UNREAD + "#" +address;
    }
}
