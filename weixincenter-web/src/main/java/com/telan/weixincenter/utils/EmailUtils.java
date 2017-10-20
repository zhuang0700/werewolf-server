package com.telan.weixincenter.utils;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	public static void receive(){
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// 定义连接POP3服务器的属性信息
		String pop3Server = "pop.qq.com";
		String protocol = "pop3";
		String username = "280812631@qq.com";
		String password = "qvasdwaqmqkjcach"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？

		Properties props = new Properties();
		props.setProperty("mail.store.protocol", protocol); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.imap.host", pop3Server); // 发件人的邮箱的 imap服务器地址
		props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.socketFactory.port", "995");
		props.setProperty("mail.pop3.auth", "true");

		// 获取连接
		Store store = null;
		Folder folder = null;// 获得用户的邮件帐户
		try {
			Session session = Session.getDefaultInstance(props);
			session.setDebug(false);

			// 获取Store对象
			store = session.getStore(protocol);
			store.connect(pop3Server, username, password); // POP3服务器的登陆认证

			// 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

			Message[] messages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,folder.getMessageCount());// 得到邮箱帐户中的所有邮件
			parseMessage(messages); //解析邮件

			folder.close(false);// 关闭邮件夹对象
			store.close(); // 关闭连接对象
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void parseMessage(Message ...messages) throws MessagingException, IOException {
		if (messages == null || messages.length < 1)
			throw new MessagingException("未找到要解析的邮件!");
		for (Message message : messages) {
			MimeMessage msg = (MimeMessage) message;
			msg.setFlag(Flags.Flag.SEEN, true);
			String subject = message.getSubject();// 获得邮件主题
			Address from = (Address) message.getFrom()[0];// 获得发送者地址
			System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from.toString());
			System.out.println("邮件的内容为：");
			message.writeTo(System.out);// 输出邮件内容到控制台
		}
	}
}