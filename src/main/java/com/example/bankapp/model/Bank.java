package com.example.bankapp.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameBank;
    @OneToMany
    @JoinTable(name = "banks_accounts",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts;
    @Column(name = "transfer_fee")
    private BigDecimal totalTransactionFeeAmount;
    @Column(name = "transfers_amount")
    private BigDecimal totalTransferAmount;
    @Column(name = "flat_fee")
    private BigDecimal flatFee;
    @Column(name = "percent_fee")
    private BigDecimal percentFee;
}
