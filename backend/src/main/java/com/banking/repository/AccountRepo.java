package com.banking.repository;

import com.banking.model.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {

    @Query(value = "SELECT * FROM ACCOUNTS WHERE ACC_NO = :ACC_NO",nativeQuery = true)
    List<Account> findByAccNo(@Param("ACC_NO") String accNo);

    @Transactional
    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Query(value = "UPDATE ACCOUNTS SET ACC_BALANCE = :ACC_BALANCE WHERE ACC_NO = :ACC_NO AND ACTIVE = :ACTIVE",nativeQuery = true)
    public Integer updateAccountBalance(@Param("ACC_BALANCE")Double accBalance,
                                        @Param("ACC_NO")String accNo,
                                        @Param("ACTIVE")String active);
}
