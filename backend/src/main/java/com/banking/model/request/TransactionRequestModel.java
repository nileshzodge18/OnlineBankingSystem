package com.banking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransactionRequestModel {
    private String TxnId;
    private String transactionType;
    private String description;
    private String senderName;
    private String senderAccount;
    private String senderBankName;
    private String senderBankIFSCCode;
    private String senderCurrency;
    private Double senderAmount;
    private String receiverName;
    private String receiverAccount;
    private String receiverBankName;
    private String receiverBankIFSCCode;
    private String receiverCurrency;
    private Double receiverAmount;
}
