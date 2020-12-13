package com.tju.myMailServer.utils;

import com.alibaba.fastjson.JSON;
import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.inferfaces.IPacketParser;

import java.util.List;

public class PacketParser {

    public static Mail parsePacket(String mailPacket) {
        return JSON.parseObject(mailPacket, Mail.class);
    }
    public static List<Mail> parsePacketList(String mailPacket) {
        return JSON.parseArray(mailPacket, Mail.class);
    }

    public static String parseMail(Mail mail) {
        return  "-1#" + JSON.toJSONString(mail);
    }

    public static String parseRequest(Contact address, MailTypes type) {
        return type.getCode() + "#" + address.toString();
    }
}
