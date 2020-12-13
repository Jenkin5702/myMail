package com.tju.myMailServer.inferfaces;

public interface IDBAgent {
    public boolean saveMail(String mail);
    public String loadMailList(String address);
    public String deleteMail(String mail);
}
