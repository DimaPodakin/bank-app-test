package com.example.bankapp.controller;

import com.example.bankapp.dto.mapper.RequestDtoMapper;
import com.example.bankapp.dto.mapper.ResponseDtoMapper;
import com.example.bankapp.dto.request.AccountRequestDto;
import com.example.bankapp.dto.response.AccountResponseDto;
import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;
    private final ResponseDtoMapper<AccountResponseDto, Account> responseDtoMapper;
    private final RequestDtoMapper<AccountRequestDto, Account> requestDtoMapper;

    @PostMapping("/save")
    public AccountResponseDto saveNewAccount(@RequestBody AccountRequestDto account) {
        return responseDtoMapper.toDto(service.save(requestDtoMapper.toModel(account)));
    }

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable Long id) {
        return responseDtoMapper.toDto(service.findById(id));
    }
}
