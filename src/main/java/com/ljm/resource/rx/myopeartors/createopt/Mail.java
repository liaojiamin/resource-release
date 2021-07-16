package com.ljm.resource.rx.myopeartors.createopt;

public class Mail implements java.io.Serializable {
    private static final long serialVersionUID = -7639363932327495175L;
    private String title;
    private String content;
    private String nickName;
    private String receiver;
    private String ccReceiver;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCcReceiver() {
        return ccReceiver;
    }

    public void setCcReceiver(String ccReceiver) {
        this.ccReceiver = ccReceiver;
    }

}
