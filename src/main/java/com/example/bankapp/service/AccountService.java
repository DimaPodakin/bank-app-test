package com.example.bankapp.service;

import com.example.bankapp.model.Account;

public interface AccountService {
    Account save(Account account);

    Account findById(Long id);
}
