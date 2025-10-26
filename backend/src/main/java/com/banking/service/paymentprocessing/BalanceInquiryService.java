package com.banking.service.paymentprocessing;

import com.banking.model.entities.Account;
import com.banking.service.entities.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceInquiryService {

    @Autowired
    AccountService accountService;

    public Boolean doBalanceInquiryImpl(String accNo, Double senderAmount){

        List<Account> accountList = accountService.getAccount(accNo);
        if(accountList.size() != 1) {
            return false;
        }
        Account senderAccount = accountList.get(0);

        return isBalanceSufficient(senderAccount,senderAmount);



    }

    public Boolean isBalanceSufficient(Account senderAccount,Double senderAmount){
        if(senderAmount <= senderAccount.getAccBalance()){
            Double updatedAccountBalance = senderAccount.getAccBalance() - senderAmount;
            return accountService.updateAccountBalance(updatedAccountBalance,senderAccount);
        }
        return false;
    }
}
