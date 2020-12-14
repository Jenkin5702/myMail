package com.tju.myMailServer.inferfaces;

import com.tju.myMailServer.entities.Contact;
import com.tju.myMailServer.entities.Mail;

import java.util.List;

public interface IMailInteraction {
    public boolean sendMail(Mail mail);
    public boolean saveScript(Mail mail);
    public boolean deleteMail(Mail mail);
    public List<Mail> listUnread(String address);
    public List<Mail> listReceived(String address);
    public List<Mail> listSent(String address);
    public List<Mail> listScript(String address);
}
