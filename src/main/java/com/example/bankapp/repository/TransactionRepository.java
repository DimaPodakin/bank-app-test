package com.example.bankapp.repository;

import com.example.bankapp.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByToAccountId(Long id);

    List<Transaction> findAllByFromAccountId(Long id);
}
