package com.example.bankapp.repository;

import com.example.bankapp.model.Bank;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query(value = "SELECT transfer_fee FROM Banks WHERE Banks.id = ?1", nativeQuery = true)
    BigDecimal getTotalTransactionFeeAmount(Long id);

    @Query(value = "SELECT transfers_amount FROM Banks WHERE Banks.id = ?1", nativeQuery = true)
    BigDecimal getTotalTransferAmount(Long id);
}
