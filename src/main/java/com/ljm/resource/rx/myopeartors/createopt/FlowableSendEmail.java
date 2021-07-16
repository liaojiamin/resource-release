package com.ljm.resource.rx.myopeartors.createopt;

import org.apache.commons.lang3.StringUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FlowableSendEmail {
    private static final String MAIL_SMTP_HOST = "smtp.exmail.qq.com";

    private static final String MAIL_SMTP_PORT = "25";

    private static final String MAIL_NAME = "mingqijiaoyou@zhenai.com";

    private static final String MAIL_PASSWORD = "zU0YEwXB";

    private static final String MAIL_SMTP_AUTH = "true";

    public static void main(String[] args) {
        for (int i = 0 ;i<1000;i++){
            send("要买码自己去买，TMD " + i, "mulanliu1986@126.com");
            send("要买码自己去买，TMD " + i, "huaanzsj@126.com");
        }
//        Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
//                for (int i = 0; i < 1000; i++) {
//                    send("要买码自己去买，TMD " + i, "mulanliu1986@126.com");
//                    send("要买码自己去买，TMD " + i, "huaanzsj@126.com");
//                    emitter.onNext("要买码自己去买，TMD " + i);
//                }
//            }
//        }, BackpressureStrategy.ERROR).parallel(100).runOn(Schedulers.io())
//                .flatMap(new Function<String, Publisher<String>>() {
//                    @Override
//                    public Publisher<String> apply(String str) throws Exception {
////                        send(str, "mulanliu1986@126.com");
////                        send(str, "huaanzsj@126.com");
//                        System.out.println(str);
//                        return Flowable.just("SUCCESS");
//                    }
//                }).runOn(Schedulers.newThread()).sequential().subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String integer) throws Exception {
////                System.out.println(Thread.currentThread().getName() + " : "+ integer +" onConsumer");
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                throwable.printStackTrace();
//            }
//        });
//        try {
//            Thread.sleep(9000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void send(String mycontent, String mysender) {
        String email = mycontent;
        Mail mail = new Mail();
        mail.setContent(email);
        mail.setReceiver(mysender);
        mail.setTitle(mycontent);
        MyAuthenticator authenticator = new MyAuthenticator(MAIL_NAME, MAIL_PASSWORD);
        Properties pro = new Properties();
        pro.put("mail.smtp.host", MAIL_SMTP_HOST);
        pro.put("mail.smtp.port", MAIL_SMTP_PORT);
        pro.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        Session sendMailSession = Session.getInstance(pro, authenticator);
        Message mailMessage = new MimeMessage(sendMailSession);
        Map<String, Object> logMap = new HashMap<String, Object>();
        logMap.put("message-type", "mail");
        try {
            Address from = null;
            // 设置邮件消息的发送者
            String nickName = mail.getNickName();
            String sender = MAIL_NAME;
            if (nickName != null && !nickName.equals("")) {// 是否设置昵称
                try {
                    nickName = MimeUtility.encodeText(nickName);
                    from = new InternetAddress(nickName + "<" + sender + ">");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                from = new InternetAddress(sender);
            }
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            String receiver = mail.getReceiver();
            String receivers = buildReceiverList(receiver);
            new InternetAddress();
            Address[] to = InternetAddress.parse(receivers);
            mailMessage.setRecipients(Message.RecipientType.TO, to);
            String ccReceiver = mail.getCcReceiver();
            if (StringUtils.isNoneBlank(ccReceiver)) {// 不为空
                String ccReceiverList = buildReceiverList(ccReceiver);
                InternetAddress[] iaToListcs = InternetAddress.parse(ccReceiverList);
                mailMessage.addRecipients(Message.RecipientType.CC, iaToListcs);// 抄送人
            }
            mailMessage.setSubject(mail.getTitle());
            mailMessage.setSentDate(new Date());
            Multipart mainPart = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(mail.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            mailMessage.setContent(mainPart);
            logMap.put("sender", sender);
            logMap.put("receiver", receiver);
            logMap.put("content", mail.getContent());
            logMap.put("subject", mail.getTitle());
            Transport.send(mailMessage);
            logMap.put("result", "success");
            System.out.println("send msg mulanliu1986@126.com success :" + mycontent);
        } catch (Throwable e) {
        }

    }

    /**
     * 组装邮箱接收人
     */
    private static String buildReceiverList(String mailRecevier) {
        String[] mailRecevierArray = {mailRecevier};// 默认为自己
        if (mailRecevier.contains(",")) {
            mailRecevierArray = mailRecevier.split(",");
        }
        if (mailRecevier.contains(";")) {
            mailRecevierArray = mailRecevier.split(";");
        }
        StringBuffer toList = new StringBuffer();
        for (String mailSonRecevier : mailRecevierArray) {
            toList.append(mailSonRecevier);
            toList.append(",");
        }
        String toListStr = toList.toString();
        if (toListStr.endsWith(",")) {
            toListStr = toListStr.substring(0, toListStr.length() - 1);
        }
        return toListStr;
    }

}
