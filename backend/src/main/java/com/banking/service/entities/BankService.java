package com.banking.service.entities;


import com.banking.model.entities.Bank;
import com.banking.repository.BankRepo;
import static com.banking.utilities.GlobalConstants.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;

    public Bank getLocalBank(){
        List<Bank> localBankList =  bankRepo.getLocalBank(TRUE,REC_AC);
        if(ObjectUtils.isEmpty(localBankList))
            return null;
        return localBankList.get(0);
    }

    public boolean checkIfReceiverBankExists(String receiverBank){
        List<Bank> bankList = bankRepo.checkIfReceiverBankExists(receiverBank,FALSE,REC_AC);
        return !ObjectUtils.isEmpty(bankList) && !bankList.isEmpty();

    }

    public Bank getBankByIFSCCode(String receiverIFSCCode){
        List<Bank> bankList;
        if(getLocalBank().getBankIFSCCode().equals(receiverIFSCCode))
            bankList = bankRepo.checkIfReceiverIFSCExists(receiverIFSCCode,TRUE,REC_AC);
        else
            bankList = bankRepo.checkIfReceiverIFSCExists(receiverIFSCCode,FALSE,REC_AC);

        return bankList.get(0);
    }
}

