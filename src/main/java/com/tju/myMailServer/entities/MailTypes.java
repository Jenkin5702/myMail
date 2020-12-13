package com.tju.myMailServer.entities;

public enum MailTypes {
    RECEIVED(0),
    UNREAD(1),
    SENT(2),
    SCRIPT(3),
    TRASH(4),
    TO_DELETE(5);

    private final int code;
    MailTypes(int i) {
        this.code=i;
    }

    public int getCode() {
        return code;
    }
}
