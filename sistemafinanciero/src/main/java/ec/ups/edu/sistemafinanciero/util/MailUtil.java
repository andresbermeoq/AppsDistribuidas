package ec.ups.edu.sistemafinanciero.util;

import java.net.PortUnreachableException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	public static void enviarCorreo(String destinatario, String asunto, String cuerpo) throws AddressException, MessagingException {
		
		String remitente = "";
		String claveString = "";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); //Servidor de Google
		properties.put("mail.smtp.port", "587");  //Puerto Seguro de Google
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");  //Conexion al Servidor SMTP de manera segura
		properties.put("mail.smtp.user", remitente); 
		properties.put("mail.smtp.clave", claveString); 
		
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message= new MimeMessage(session);
		

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		message.setSubject(asunto);
		message.setText(cuerpo, "utf-8", "html");
		
		Transport transport= session.getTransport("smtp");
		transport.connect("smtp.gmail.com", remitente, claveString);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("Correo Enviado");
	}

}
