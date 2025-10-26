package com.banking.repository;

import com.banking.model.entities.TxnError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnErrorRepo extends JpaRepository<TxnError,Integer> {
}
