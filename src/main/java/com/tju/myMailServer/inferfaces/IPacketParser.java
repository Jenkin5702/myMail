package com.tju.myMailServer.inferfaces;

import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;
import com.tju.myMailServer.entities.MailTypes;

public interface IPacketParser {
    public Mail parsePacket(String mailPacket);
    public String parseMail(Mail mail);
    public String parseRequest(Contact address, MailTypes type);
}
