package com.ljm.resource.netty.chat.protocal.packet;

import com.ljm.resource.netty.chat.protocal.commond.Commond;
/**
 * @author liaojiamin
 * @Date:Created in 17:59 2021/11/2
 */
public class MsgPacket extends Packet {

    /**
     * 序号
     */
    private Integer no;

    /**
     * 会话ID
     */
    private Integer session;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    public MsgPacket(){}
    public MsgPacket(Integer no, Integer session, Integer userId, String content){
        this.no = no;
        this.session = session;
        this.userId = userId;
        this.content = content;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Byte getCommand() {
        return Commond.MSG;
    }
}
