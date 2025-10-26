package com.banking.controller.entities.request;

import com.banking.model.request.TransactionRequestModel;
import com.banking.service.paymentprocessing.TransactionProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TransactionRequest {

    @Autowired
    TransactionProcessing transactionProcessing;

    @PostMapping
    @RequestMapping("/createTransaction")
    public ResponseEntity<TransactionRequestModel> createTransaction(@RequestBody TransactionRequestModel transactionRequestModel) {
        transactionProcessing.setTransactionRequestModel(transactionRequestModel);
        HttpStatus statusCode = transactionProcessing.processCreateTransaction();
        return new ResponseEntity<>(statusCode);
    }
}
