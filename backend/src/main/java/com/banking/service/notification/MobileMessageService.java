package com.banking.service.notification;

import com.banking.model.request.TransactionRequestModel;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.notificationapi.NotificationApi;
import com.notificationapi.model.*;

@Service
public class MobileMessageService {

    private static final Logger logger = LoggerFactory.getLogger(MobileMessageService.class);

    @Value("${notification.client-id}")
    private String clientId;

    @Value("${notification.client-secret}")
    private String clientSecret;


    public void sendMessage(TransactionRequestModel transactionRequestModel) {

        try {
            NotificationApi api = new NotificationApi(
                    clientId,
                    clientSecret
            );

            // Create user
            User user = new User("nileshzodge@gmail.com")
                    .setNumber("+919766868395"); // Replace with your phone number, use format [+][country code][area code][local number];

            // Create and send notification request
            NotificationRequest request = new NotificationRequest("transactionnotification", user)
                    .setSms(new SmsOptions()
                            .setMessage("Hello Again New World Sending for mobile message")
                    );

            System.out.println("Sending notification request...");
            String response = api.send(request);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            logger.error("Error encountered during sending Notification");
        }

    }
}
