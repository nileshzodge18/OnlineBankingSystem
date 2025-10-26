package com.banking.repository;

import com.banking.model.entities.Exch_Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchRateRepo extends JpaRepository<Exch_Rate,Integer> {

    @Query(value = "SELECT * FROM EXCH_RATES WHERE CCY1 = :CCY1 AND CCY2 = :CCY2 LIMIT 1",nativeQuery = true)
    Exch_Rate getExchRateProfile(@Param("CCY1") String ccy1,
                                 @Param("CCY2") String ccy2);
}
