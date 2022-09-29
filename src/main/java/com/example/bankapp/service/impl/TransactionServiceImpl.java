package com.example.bankapp.service.impl;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Bank;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.BankService;
import com.example.bankapp.service.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final BankService bankService;
    private final AccountService accountService;

    @Override
    public String transactionByFlatFee(Transaction transaction, Long bankId) {
        Bank bank = bankService.findById(bankId);
        Account fromAccount = accountService.findById(transaction.getFromAccountId());
        Account toAccount = accountService.findById(transaction.getToAccountId());
        return checkAmountWithFlatFee(bank, fromAccount, toAccount, transaction);
    }

    @Override
    public String transactionByPercentFee(Transaction transaction, Long bankId) {
        Bank bank = bankService.findById(bankId);
        Account fromAccount = accountService.findById(transaction.getFromAccountId());
        Account toAccount = accountService.findById(transaction.getToAccountId());
        return checkAmountWithPercentFee(bank, fromAccount, toAccount, transaction);
    }

    @Override
    public String withdrawalFromAccount(Transaction transaction) {
        Account account = accountService.findById(transaction.getFromAccountId());
        BigDecimal amountAccount = account.getAmount();
        if (amountAccount.compareTo(transaction.getAmount()) > 0) {
            account.setAmount(amountAccount.subtract(transaction.getAmount()));
            accountService.save(account);
            repository.save(transaction);
            return "Cash was withdrawn successfully in the amount: " + transaction.getAmount();
        }
        return "There are not enough funds in the account!";
    }

    @Override
    public String depositToAccount(Transaction transaction) {
        Account account = accountService.findById(transaction.getToAccountId());
        account.setAmount(account.getAmount().add(transaction.getAmount()));
        accountService.save(account);
        repository.save(transaction);
        return "The account was successfully replenished by the amount: " + transaction.getAmount();
    }

    @Override
    public List<Transaction> getHistory(Long accountId) {
        List<Transaction> listToAccountId = repository.findAllByToAccountId(accountId);
        listToAccountId.addAll(repository.findAllByFromAccountId(accountId));
        return listToAccountId;
    }

    private String checkAmountWithPercentFee(Bank bank, Account fromAccount,
                                             Account toAccount, Transaction transaction) {
        BigDecimal percentFee = Optional.ofNullable(bank.getPercentFee())
                .orElseGet(() -> new BigDecimal("1"));
        BigDecimal amountFromAccount = fromAccount.getAmount();
        BigDecimal transferAmount = transaction.getAmount();
        BigDecimal totalPercentFee = transferAmount.multiply(
                percentFee.divide(new BigDecimal("100")));
        BigDecimal totalTransfer = totalPercentFee.add(transferAmount);

        if (amountFromAccount.compareTo(totalTransfer) > 0) {
            setData(amountFromAccount, totalTransfer, fromAccount,
                    toAccount, bank, transferAmount);
            bank.setTotalTransactionFeeAmount(
                    bank.getTotalTransactionFeeAmount().add(totalPercentFee));
            commit(fromAccount, toAccount, bank, transaction);
            return "The transaction was successful. Commission was " + totalPercentFee;
        }
        return "There are not enough funds in the account!";
    }

    private String checkAmountWithFlatFee(Bank bank, Account fromAccount,
                                          Account toAccount, Transaction transaction) {
        BigDecimal flatFee = Optional.ofNullable(bank.getFlatFee())
                .orElseGet(() -> new BigDecimal("0"));
        BigDecimal amountFromAccount = fromAccount.getAmount();
        BigDecimal transferAmount = transaction.getAmount();
        BigDecimal totalTransfer = transferAmount.add(flatFee);

        if (amountFromAccount.compareTo(totalTransfer) > 0) {
            setData(amountFromAccount, totalTransfer, fromAccount, toAccount, bank, transferAmount);
            bank.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount().add(flatFee));
            commit(fromAccount, toAccount, bank, transaction);
            return "The transaction was successful. Commission was " + flatFee;
        }
        return "There are not enough funds in the account!";
    }

    private void setData(BigDecimal amountFromAccount, BigDecimal totalTransfer,
                         Account fromAccount, Account toAccount,
                         Bank bank, BigDecimal transferAmount) {
        BigDecimal totalAmount = amountFromAccount.subtract(totalTransfer);
        BigDecimal addedTotalAmount = toAccount.getAmount().add(transferAmount);

        fromAccount.setAmount(totalAmount);
        toAccount.setAmount(addedTotalAmount);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount().add(transferAmount));
    }

    private void commit(Account fromAccount, Account toAccount,
                        Bank bank, Transaction transaction) {
        accountService.save(fromAccount);
        accountService.save(toAccount);
        bankService.save(bank);
        repository.save(transaction);
    }
}
