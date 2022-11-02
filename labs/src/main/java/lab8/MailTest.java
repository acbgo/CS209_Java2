package lab8;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class MailTest
{
   public static void main(String[] args) throws MessagingException, IOException
   {
      // read properties
      Properties props = new Properties();
      try (InputStream in = Files.newInputStream(Paths.get("E:\\OneDrive - 南方科技大学\\code\\CS209_Java2\\labs\\src\\main\\java\\lab8\\mail.properties")))
      {
         props.load(in);
      }

      // read message info
      List<String> lines = Files.readAllLines(Paths.get("E:\\OneDrive - 南方科技大学\\code\\CS209_Java2\\labs\\src\\main\\java\\lab8\\message.txt"), StandardCharsets.UTF_8);

      String from = lines.get(0);
      String to = lines.get(1);
      String subject = lines.get(2);

      StringBuilder builder = new StringBuilder();
      for (int i = 3; i < lines.size(); i++)
      {
         builder.append(lines.get(i));
         builder.append("\n");
      }


      // read password for your email account
      System.out.println("Password: ");
      Scanner scanner = new Scanner(System.in);
      String password = scanner.next();


      Session mailSession = Session.getDefaultInstance(props);
      mailSession.setDebug(true);
      Transport ts = mailSession.getTransport();
      ts.connect("smtp.sustech.edu.cn","12011118@mail.sustech.edu.cn",password);
      MimeMessage message = new MimeMessage(mailSession);
      message.setFrom(from);
      message.setRecipients(Message.RecipientType.TO, to);
      message.setSubject(subject);
      message.setContent(builder.toString(),"text/html;charset=UTF-8");
      ts.sendMessage(message, message.getAllRecipients());
      ts.close();
      // TODO 1: check the MimeMessage API to figure out how to set the sender, receiver, subject, and email body

      // TODO 2: check the Session API to figure out how to connect to the mail server and send the message

   }
}