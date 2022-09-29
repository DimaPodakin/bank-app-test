package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Bank;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.BankService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final AccountService accountService;
    private final BankService bankService;

    @GetMapping
    public String inject() {
        Account dima = new Account();
        dima.setUserName("Dima");
        dima.setAmount(new BigDecimal("1000"));

        Account andrii = new Account();
        andrii.setUserName("Andrii");
        andrii.setAmount(new BigDecimal("1000"));

        accountService.save(dima);
        accountService.save(andrii);

        Bank mono = new Bank();
        mono.setNameBank("Mono Bank");
        mono.setTotalTransactionFeeAmount(new BigDecimal("0"));
        mono.setTotalTransferAmount(new BigDecimal("0"));
        mono.setFlatFee(new BigDecimal("10"));
        mono.setPercentFee(new BigDecimal("5"));

        bankService.save(mono);
        return "Information has been injected!";
    }
}
