package com.banking.service.notification;

import com.banking.model.entities.Account;
import com.banking.model.entities.User;
import com.banking.model.request.TransactionRequestModel;
import com.banking.service.entities.AccountService;
import com.banking.service.entities.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.banking.utilities.GlobalConstants.CREDITOR;
import static com.banking.utilities.GlobalConstants.DEBITOR;

@Service
public class NotificationService {

    @Autowired
    AccountService accountService;

    @Autowired
    UsersService usersService;

    @Autowired
    MobileMessageService mobileMessageService;

    @Autowired
    EmailService emailService;

    @Autowired
    CallService callService;


    public void sendNotifications(TransactionRequestModel transactionRequestModel,String AccountType){
        switch (AccountType) {
            case DEBITOR:
                sendNotificationsToDebitor(transactionRequestModel);
            case CREDITOR:
                sendNotificationsToCreditor(transactionRequestModel);
        }

    }

    private void sendNotificationsToCreditor(TransactionRequestModel transactionRequestModel) {
        String creditorAcc = transactionRequestModel.getReceiverAccount();
        List<Account> creditorAccountList = accountService.getAccount(creditorAcc);

        Account creditorAccount = creditorAccountList.get(0);
        Integer uidUser = creditorAccount.getUidUsers();

        Optional<User> creditorUserOptional = usersService.retrieveUserDetails(uidUser);

        if(creditorUserOptional.isPresent()){
            User creditorUser = creditorUserOptional.get();
            if(creditorUser.getMobile_no() != null && !creditorUser.getMobile_no().isEmpty()){
                mobileMessageService.sendMessage(transactionRequestModel);
                callService.callUser(transactionRequestModel);
            }

            if(creditorUser.getEmail() != null && !creditorUser.getEmail().isEmpty()){
                emailService.sendEmail(transactionRequestModel);
            }

        }

    }

    private void sendNotificationsToDebitor(TransactionRequestModel transactionRequestModel) {
        String debitorAcc = transactionRequestModel.getSenderAccount();
        List<Account> creditorAccountList = accountService.getAccount(debitorAcc);

        Account debitorAccount = creditorAccountList.get(0);
        Integer uidUser = debitorAccount.getUidUsers();

        Optional<User> debitorUserOptional = usersService.retrieveUserDetails(uidUser);

        if(debitorUserOptional.isPresent()){
            User debitorUser = debitorUserOptional.get();
            if(debitorUser.getMobile_no() != null && !debitorUser.getMobile_no().isEmpty()){
                mobileMessageService.sendMessage(transactionRequestModel);

            }

            if(debitorUser.getEmail() != null && !debitorUser.getEmail().isEmpty()){
                emailService.sendEmail(transactionRequestModel);
            }

        }
    }


    }
