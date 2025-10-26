package com.banking.repository;

import com.banking.model.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepo extends JpaRepository<Audit,Integer> {
}
