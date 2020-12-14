package com.tju.myMailServer.entities;

import java.util.Date;
import com.alibaba.fastjson.*;

public class Mail {
    private MailTypes status;
    private String title;
    private String from;
    private String to;
    private long time;
    private String supplement;
    private String content;

    public Mail(String title, String from, String to, long time, String supplement, String content) {
        this.status = MailTypes.SCRIPT;
        this.title = title;
        this.from = from;
        this.to = to;
        this.time = time;
        this.supplement = supplement;
        this.content = content;
    }

    public MailTypes getStatus() {
        return status;
    }

    public void setStatus(MailTypes status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
