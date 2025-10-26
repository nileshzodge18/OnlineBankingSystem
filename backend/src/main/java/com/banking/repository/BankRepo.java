package com.banking.repository;

import com.banking.model.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepo extends JpaRepository<Bank,Integer> {

    @Query(value = "SELECT * FROM BANKS WHERE LOCAL_BANK = :LOCAL_BANK AND ACTIVE = :ACTIVE",nativeQuery = true)
    public List<Bank> getLocalBank(@Param("LOCAL_BANK")Boolean localBank,
                                   @Param("ACTIVE")String active);

    @Query(value = "SELECT * FROM BANKS WHERE BANK_NAME = :BANK_NAME AND LOCAL_BANK = :LOCAL_BANK AND ACTIVE = :ACTIVE",nativeQuery = true)
    public List<Bank> checkIfReceiverBankExists(@Param("BANK_NAME")String bankName,
                                                @Param("LOCAL_BANK")Boolean localBank,
                                                @Param("ACTIVE")String active);

    @Query(value = "SELECT * FROM BANKS WHERE BANK_IFSC = :BANK_IFSC AND LOCAL_BANK = :LOCAL_BANK AND ACTIVE = :ACTIVE",nativeQuery = true)
    public List<Bank> checkIfReceiverIFSCExists(@Param("BANK_IFSC")String bankIFSC,
                                                @Param("LOCAL_BANK")Boolean localBank,
                                                @Param("ACTIVE")String active);
}
