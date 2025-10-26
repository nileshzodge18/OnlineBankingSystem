package com.banking.service.paymentprocessing;

import com.banking.model.entities.Exch_Rate;
import com.banking.model.request.TransactionRequestModel;
import com.banking.service.entities.ExchRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.banking.utilities.GlobalConstants.OPERATION_TYPE_DIVIDE;
import static com.banking.utilities.GlobalConstants.OPERATION_TYPE_MULTIPLY;

@Service
public class ForeignExchService {
    @Autowired
    ExchRateService exchRateService;

    StringBuilder operationType = new StringBuilder();

    public static Logger logger = LoggerFactory.getLogger(ForeignExchService.class);

    public void doFXConversion(TransactionRequestModel transactionRequestModel) {

        if(transactionRequestModel.getSenderCurrency().equals(transactionRequestModel.getReceiverCurrency()))
            return;

        String CCY1 = transactionRequestModel.getSenderCurrency();
        String CCY2 = transactionRequestModel.getReceiverCurrency();

        Exch_Rate exchRate = exchRateService.getExchRateProfile(CCY1,CCY2,operationType);

        Double convRate = exchRate.getConvRate();

        Double senderAmount = transactionRequestModel.getSenderAmount();
        if(operationType.toString().equals(OPERATION_TYPE_MULTIPLY)) {
            transactionRequestModel.setReceiverAmount(senderAmount * convRate);
        }
        else if(operationType.toString().equals(OPERATION_TYPE_DIVIDE)){
            transactionRequestModel.setReceiverAmount(senderAmount / convRate);

        }
        else {
            logger.error("Invalid Operation Type");
        }



    }
}
