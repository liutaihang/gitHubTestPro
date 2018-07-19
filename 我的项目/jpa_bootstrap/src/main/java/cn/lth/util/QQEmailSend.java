package cn.lth.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author liutaihang
 * @Title: jpa_bootstrap
 * @Package cn.lth.util
 * @Description: ${todo}
 * @date 2018/7/1717:39
 */
public class QQEmailSend {
    public static String myEmail = "1576756228@qq.com";
    public static String verifyCode = "lawjmwbqxuimhdac";


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");       //开启debug调试
        properties.setProperty("mail.smtp.auth","true");    //发送服务器需要验证
        properties.setProperty("mail.host","smtp.qq.com");  //设置邮件服务器主机名
        properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议名称

        MailSSLSocketFactory sl = new MailSSLSocketFactory();//开启ssl加密
        sl.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sl);

        Session session = Session.getInstance(properties);
        Transport ts = session.getTransport();
        ts.connect("smtp.qq.com", "1576756228", verifyCode);
        MimeMessage mail = createSimpleMail(session);
        ts.sendMessage(mail, mail.getAllRecipients());
    }

    public static MimeMessage createSimpleMail(Session session) throws MessagingException {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("1576756228@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1576756228@qq.com"));
        // 邮件的标题
        message.setSubject("mister.liu.mail.demo");
        // 邮件的文本内容
        message.setContent("mister.liu.mail.send.success", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
