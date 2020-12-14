package com.tju.myMailServer.client;

import com.tju.myMailServer.communication.SocketLink;
import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;
import com.tju.myMailServer.inferfaces.IMailInteraction;
import com.tju.myMailServer.utils.PacketParser;

import java.util.List;

public class MailClient implements IMailInteraction {

    @Override
    public boolean sendMail(Mail mail) {
        mail.setStatus(MailTypes.SENT);
        return SocketLink.post(PacketParser.parseMail(mail));
    }

    @Override
    public boolean saveScript(Mail mail) {
        mail.setStatus(MailTypes.SCRIPT);
        return SocketLink.post(PacketParser.parseMail(mail));
    }

    @Override
    public boolean deleteMail(Mail mail) {
        mail.setStatus(MailTypes.TO_DELETE);
        return SocketLink.post(PacketParser.parseMail(mail));
    }

    @Override
    public List<Mail> listUnread(String address) {
        String listStr = SocketLink.request(PacketParser.parseRequest(address, MailTypes.UNREAD));
        return PacketParser.parsePacketList(listStr);
    }

    @Override
    public List<Mail> listReceived(String address) {
        String listStr = SocketLink.request(PacketParser.parseRequest(address, MailTypes.RECEIVED));
        return PacketParser.parsePacketList(listStr);
    }

    @Override
    public List<Mail> listSent(String address) {
        String listStr = SocketLink.request(PacketParser.parseRequest(address, MailTypes.SENT));
        return PacketParser.parsePacketList(listStr);
    }

    @Override
    public List<Mail> listScript(String address) {
        String listStr = SocketLink.request(PacketParser.parseRequest(address, MailTypes.SCRIPT));
        System.out.println(listStr);
        return PacketParser.parsePacketList(listStr);
    }
}