//package com.example.realestateapp.MailManagement.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendSignupEmail(String to, String username) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(to);
//            message.setSubject("Welcome to Real Estate App – Your Account Has Been Created!");
//            message.setText("Hi " + username + ",\n\n" +
//                    "Thank you for registering with Real Estate App!\n\n" +
//                    "Your account has been successfully created. You can now browse, list, and manage properties with ease. We’re excited to have you on board!\n\n" +
//                    "If you have any questions or need assistance, feel free to contact our support team.\n\n" +
//                    "Best regards,\n" +
//                    "Real Estate App Team\n");
//
//            mailSender.send(message);
//            System.out.println("✅ Email sent to " + to);
//        } catch (Exception e) {
//            System.out.println("❌ Failed to send email to " + to);
//            e.printStackTrace();
//        }
//    }
//}








package com.example.realestateapp.MailManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSignupEmail(String to, String username) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Real Estate App – Your Account Has Been Created!");
            message.setText("Hi " + username + ",\n\n" +
                    "Thank you for registering with Real Estate App!\n\n" +
                    "Your account has been successfully created. You can now browse, list, and manage properties with ease. We’re excited to have you on board!\n\n" +
                    "If you have any questions or need assistance, feel free to contact our support team.\n\n" +
                    "Best regards,\n" +
                    "Real Estate App Team\n");

            mailSender.send(message);
            System.out.println("✅ Signup email sent to " + to);
        } catch (Exception e) {
            System.out.println("❌ Failed to send signup email to " + to);
            e.printStackTrace();
        }
    }

    public void sendPaymentReceipt(String username, String receiptText) {
        String to = getEmailByUsername(username);
        if (to == null) {
            System.out.println("❌ Email not found for user: " + username);
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Payment Receipt – Real Estate App");

            String body = "Hi " + username + ",\n\n" +
                    "Thank you for your recent payment on Real Estate App. Here are your transaction details:\n\n" +
                    "------------------------------------------\n" +
                    receiptText +
                    "------------------------------------------\n\n" +
                    "This receipt confirms that your payment was successfully processed. If you have any questions, feel free to reach out.\n\n" +
                    "Best regards,\n" +
                    "Real Estate App Billing Team";

            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Payment receipt email sent to " + to);
        } catch (Exception e) {
            System.out.println("❌ Failed to send payment receipt to " + to);
            e.printStackTrace();
        }
    }


    private String getEmailByUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    return parts[2]; // assuming format: username,password,email
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
