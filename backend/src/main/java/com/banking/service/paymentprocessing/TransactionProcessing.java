package com.banking.service.paymentprocessing;

import com.banking.model.entities.Bank;
import com.banking.model.request.TransactionRequestModel;
import com.banking.service.entities.AccountService;
import com.banking.service.entities.BankService;
import com.banking.service.notification.NotificationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

import static com.banking.utilities.GlobalConstants.*;

@Service
public class TransactionProcessing {

    @Setter
    private TransactionRequestModel transactionRequestModel;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankService bankService;

    @Autowired
    private BalanceInquiryService balanceInquiryService;

    @Autowired
    private SanctionsInquiryService sanctionsInquiryService;

    @Autowired
    private ForeignExchService foreignExchService;

    @Autowired
    private FinalSettlementService finalSettlementService;

    @Autowired
    private NotificationService notificationService;


    public HttpStatus processCreateTransaction() {

        if (ObjectUtils.isEmpty(transactionRequestModel))
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if(!setInitializationDetails())
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if (!isDebitorValid())
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if(!isCreditorValid())
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if(doSanctionScreening())
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if(!doBalanceInquiry())
            return HttpStatus.INTERNAL_SERVER_ERROR;

        if(checkIfFXConversionIsRequired()){
            doFXConversion();

        }

        if (checkIfReceiverFromSameBank()) {
            Boolean finalSettlementSuccessful = doFinalSettlementInBank();

            if(finalSettlementSuccessful){
                notificationService.sendNotifications(transactionRequestModel,CREDITOR);


            }
        }



        return HttpStatus.NO_CONTENT;
    }

    private Boolean doFinalSettlementInBank() {
        return finalSettlementService.doFinalSettlementInBank(transactionRequestModel);
    }

    private boolean checkIfReceiverFromSameBank() {

        return transactionRequestModel.getSenderBankIFSCCode().equals(transactionRequestModel.getSenderBankIFSCCode());


    }

    private void doFXConversion() {
        foreignExchService.doFXConversion(transactionRequestModel);
    //TODO
    }

    private boolean checkIfFXConversionIsRequired() {

        return !transactionRequestModel.getSenderCurrency().equals(transactionRequestModel.getReceiverCurrency());
    }

    private boolean doSanctionScreening() {
        return sanctionsInquiryService.doSanctionScreeningImpl(transactionRequestModel);

    }

    public boolean isDebitorValid(){
        String senderAccount = transactionRequestModel.getSenderAccount();
        if(ObjectUtils.isEmpty(senderAccount))
            return false;

        return accountService.retrieveUserByAccountNumber(senderAccount);

    }
    public boolean setInitializationDetails(){
        Bank localBank = bankService.getLocalBank();

        if(ObjectUtils.isEmpty(localBank))
            return false;

        transactionRequestModel.setSenderBankName(localBank.getBankName());
        transactionRequestModel.setSenderBankIFSCCode(localBank.getBankIFSCCode());
        setTXNID();


        return true;

    }

    private void setTXNID() {

        String PREFIX = "TXN-";
        String randId = UUID.randomUUID().toString().toUpperCase().replace("-","");
        randId = randId.substring(0,7);
        int randNum = (int) (Math.random()*10000);
        String randSuffix = String.format("%05d",randNum);
        transactionRequestModel.setTxnId(PREFIX + randId + randSuffix);
    }

    public boolean isCreditorValid(){

        if(isCreditorFromSameBankAsDebitor()){
            return accountService.checkIfAccountExists(transactionRequestModel.getReceiverAccount());
        }
        return isReceiverBankValid();
    }

    public boolean isCreditorFromSameBankAsDebitor(){
        String senderBankIFSC = bankService.getLocalBank().getBankIFSCCode();
        String receiverBankIFSC = transactionRequestModel.getReceiverBankIFSCCode();
        return senderBankIFSC.equals(receiverBankIFSC);

    }

    public boolean isReceiverBankValid(){
        return bankService.checkIfReceiverBankExists(transactionRequestModel.getReceiverBankName());
    }


    public boolean doBalanceInquiry(){
        return balanceInquiryService.doBalanceInquiryImpl(transactionRequestModel.getSenderAccount(),transactionRequestModel.getSenderAmount());
    }
}

