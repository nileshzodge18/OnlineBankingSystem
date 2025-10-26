package com.banking.service.paymentprocessing;

import com.banking.model.entities.Account;
import com.banking.model.request.TransactionRequestModel;
import com.banking.service.entities.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalSettlementService {

    @Autowired
    AccountService accountService;

    public boolean doFinalSettlementInBank(TransactionRequestModel transactionRequestModel){
       return creditAmountInCreditorAccount(transactionRequestModel);
    }

    public Boolean creditAmountInCreditorAccount(TransactionRequestModel transactionRequestModel){
        Double receiverAmount = transactionRequestModel.getReceiverAmount();

        List<Account> accountList = accountService.getAccount(transactionRequestModel.getReceiverAccount());
        if(accountList.size() != 1) {
            return false;
        }
        Account receiverAccount = accountList.get(0);

        Double updatedAccountBalance = receiverAccount.getAccBalance() + receiverAmount;
        return accountService.updateAccountBalance(updatedAccountBalance,receiverAccount);

    }
}
