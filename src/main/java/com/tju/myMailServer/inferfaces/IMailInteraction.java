package com.tju.myMailServer.inferfaces;

import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;

import java.util.List;

public interface IMailInteraction {
    public boolean sendMail(Mail mail);
    public boolean saveScript(Mail mail);
    public boolean deleteMail(Mail mail);
    public List<Mail> listUnread(Contact address);
    public List<Mail> listReceived(Contact address);
    public List<Mail> listSent(Contact address);
    public List<Mail> listScript(Contact address);
}
