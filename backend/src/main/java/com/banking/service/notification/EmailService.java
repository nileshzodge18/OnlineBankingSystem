package com.banking.service.notification;

import com.banking.model.request.TransactionRequestModel;
import com.notificationapi.NotificationApi;
import com.notificationapi.model.EmailOptions;
import com.notificationapi.model.NotificationRequest;
import com.notificationapi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${notification.client-id}")
    private String clientId;

    @Value("${notification.client-secret}")
    private String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(MobileMessageService.class);

    public void sendEmail(TransactionRequestModel transactionRequestModel) {

        try {
            NotificationApi api = new NotificationApi(
                    clientId,
                    clientSecret
            );

            // Create user
            User user = new User("nileshzodge@gmail.com")
                    .setEmail("nileshzodge@gmail.com");

            // Create and send notification request
            NotificationRequest request = new NotificationRequest("transactionnotification", user)
                    .setEmail(new EmailOptions()
                            .setSubject("New Subject For Transaction Notification")
                            .setSenderEmail("localBank@onlinebankingsystem.com")
                            .setSenderName("Online Banking System")
                            .setPreviewText("Transaction Notification from Online System Bank")
                            .setHtml("Hello Again New World. Sending For Email")
                    );

            System.out.println("Sending notification request...");
            String response = api.send(request);
            System.out.println("Response: " + response);
        }
        catch(Exception ex){
            logger.error("Error encountered during sending Notification");
        }

    }
}
