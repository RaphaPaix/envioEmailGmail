package com.enviandoemail.jdev;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	private String userName = "raphapaixjdev@gmail.com";
	private String password = "3h2e9l6i";
	@Test
	public void testeEmail() {
		try {
			//olhe as configuraçoes do smtp do seu e-mail
			//configuração das propriedades
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true"); //autorização
			properties.put("mail.smtp.starttls", "true"); //autenticação
			properties.put("mail.smtp.host", "smtp.gmail.com"); //servidor gmail
			properties.put("mail.smtp.port", "465"); //porta do servidor
			properties.put("mail.smtp.socketFactory.port", "465"); //especifica a porta ser conectada pelo socket
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //classe socket de conexão ao SMTP
			//configurando conexão
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});
			
			Address[] toUser = InternetAddress.parse("raphapaixjdev@gmail.com, raphapaixjdev@outlook.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName)); //de quem está enviando
			message.setRecipients(Message.RecipientType.TO, toUser); //para quem está enviando
			message.setSubject("E-mail JAVA enviado com sucesso");//assunto do email
			message.setText("Olá Rapha, parece que está funcinando bem"); //corpo do email
		
			//envio
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
