package com.banking.service.paymentprocessing;

import com.banking.model.entities.Bank;
import com.banking.model.request.TransactionRequestModel;
import com.banking.service.entities.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.banking.utilities.GlobalConstants.FALSE;
import static com.banking.utilities.GlobalConstants.TRUE;

@Service
public class SanctionsInquiryService {

    private final String NAME = "NAME";
    private final String BANK_NAME = "BANK_NAME";
    private final String COUNTRY = "COUNTRY";

    @Autowired
    private BankService bankService;

    private ClassPathResource fileRead;

    private final Logger logger = LoggerFactory.getLogger(SanctionsInquiryService.class);

    private final String sanctionNamePath = "SanctionList/Sanction_Name";
    private final String sanctionBankNamePath = "SanctionList/Sanction_BankName";
    private final String sanctionCountryPath = "SanctionList/Sanction_Country";


    public boolean doSanctionScreeningImpl(TransactionRequestModel transactionRequestModel){

        if(CheckIfTransactionContainsOFACWord(transactionRequestModel.getReceiverName(),NAME))
            return TRUE;
        if(CheckIfTransactionContainsOFACWord(transactionRequestModel.getReceiverBankName(),BANK_NAME))
            return TRUE;

        Bank bank = bankService.getBankByIFSCCode(transactionRequestModel.getReceiverBankIFSCCode());

        if(CheckIfTransactionContainsOFACWord(bank.getCountry(),COUNTRY))
            return TRUE;

        return FALSE;
    }

    public Boolean CheckIfTransactionContainsOFACWord(String data,String dataType){

        switch(dataType) {
            case NAME:
                fileRead = new ClassPathResource(sanctionNamePath);
                return CheckIfTransactionContainsOFACWordImpl(fileRead,data);

            case BANK_NAME:
                fileRead = new ClassPathResource(sanctionBankNamePath);
                return CheckIfTransactionContainsOFACWordImpl(fileRead,data);

            case COUNTRY:
                fileRead = new ClassPathResource(sanctionCountryPath);
                return CheckIfTransactionContainsOFACWordImpl(fileRead,data);

            default:
                logger.debug("No Valid Data Type Found");
        }

        return true;

    }

    public Boolean CheckIfTransactionContainsOFACWordImpl(ClassPathResource fileRead, String data){

        try {
            InputStream in = fileRead.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            List<String> sanctionDataList = br.lines().toList();

            for(String sanctionData : sanctionDataList){
                if(sanctionData.contains(data)){
                    return true;
                }
            }
        }
        catch(Exception ex){
            logger.error("Error reading File");

        }
        return false;


    }
}
