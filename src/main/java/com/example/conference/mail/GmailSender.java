package com.example.conference.mail;

import com.example.conference.entity.User;
import com.example.conference.listener.AppContextAttributeListener;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

/**
 * Class for sending mails from gmail with different purpose
 */
public class GmailSender {
    private static final Logger log = Logger.getLogger(GmailSender.class);
    public GmailSender() {
    }
    public static boolean sendWelcome(String email, String name, String surname, String lang) {
      //hidden
    }
    public static boolean sendEventWelcome(String email, String name, String surname, String address, LocalDateTime dateTime, String lang) {
       //hidden
    }
    public static boolean sendEventChange(List<User> userList, String event_name, String lang) {
      //hidden
    }

}
