package com.banking.service.entities;

import com.banking.model.entities.Account;
import com.banking.model.entities.User;
import com.banking.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static com.banking.utilities.GlobalConstants.REC_AC;

@Service
public class AccountService {


    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    UsersService usersService;

    public Optional<Account> retrieveAccountDetails(Integer uid_account){
        return accountRepo.findById(uid_account);
    }

    public List<Account> retrieveAllAccountDetails(){
        return accountRepo.findAll();
    }

    public Account saveAccountDetails(Account account){
        return accountRepo.save(account);
    }

    public HttpStatus updateAccounts(Account account){
        Optional<Account> fetchaccount = accountRepo.findById(account.getUidAccounts());
        if(!fetchaccount.isEmpty()){
            accountRepo.save(account);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus deleteAccount(Integer uid_account){

        try {
            if(accountRepo.findById(uid_account).isEmpty()) {
                accountRepo.deleteById(uid_account);
                return HttpStatus.NO_CONTENT;
            }
            else {
                return HttpStatus.NOT_FOUND;
            }
        }
        catch(Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

    public boolean retrieveUserByAccountNumber(String accNo) {
        List<Account> accountList = accountRepo.findByAccNo(accNo);
        if(accountList.isEmpty())
            return false;

        if(accountList.size()>1)
            return false;

        Account acc = accountList.get(0);
        Integer uid_user = acc.getUidUsers();

        Optional<User> userByUID = usersService.retrieveUserDetails(uid_user);

        return userByUID.isPresent();


    }

    public boolean checkIfAccountExists(String accNo){
        List<Account> accountList = accountRepo.findByAccNo(accNo);
        if(ObjectUtils.isEmpty(accountList) || accountList.size() >1 )
            return false;
        Account account = accountList.get(0);
        return account.getActive().equals("AC");
    }

    public List<Account> getAccount(String accNo){
         return accountRepo.findByAccNo(accNo);
    }

    public boolean updateAccountBalance(Double updatedAccountBalance, Account account){
        Integer rowsEffected = accountRepo.updateAccountBalance(updatedAccountBalance,account.getAccNo(),REC_AC);
        return rowsEffected == 1;
    }
}
