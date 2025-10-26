package com.banking.repository;

import com.banking.model.entities.AccStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccStatementRepo extends JpaRepository<AccStatement,Integer> {
}
