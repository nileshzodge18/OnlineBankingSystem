package com.banking.service.entities;

import com.banking.model.entities.Exch_Rate;
import com.banking.repository.ExchRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.banking.utilities.GlobalConstants.OPERATION_TYPE_DIVIDE;
import static com.banking.utilities.GlobalConstants.OPERATION_TYPE_MULTIPLY;

@Service
public class ExchRateService {

    @Autowired
    ExchRateRepo exchRateRepo;

    public Exch_Rate getExchRateProfile(String CCY1, String CCY2, StringBuilder operationType) {
        Exch_Rate exchRepo;
//        operationType = new StringBuilder();
        exchRepo = exchRateRepo.getExchRateProfile(CCY1,CCY2);

        if(!ObjectUtils.isEmpty(exchRepo)) {
            operationType.append(OPERATION_TYPE_MULTIPLY);
            return exchRepo;
        }

        operationType.append(OPERATION_TYPE_DIVIDE);
        return exchRateRepo.getExchRateProfile(CCY2,CCY1);

    }
}
