package com.example.bankapp.controller;

import com.example.bankapp.dto.mapper.RequestDtoMapper;
import com.example.bankapp.dto.mapper.ResponseDtoMapper;
import com.example.bankapp.dto.request.TransactionRequestDto;
import com.example.bankapp.dto.response.TransactionResponseDto;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.service.TransactionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService service;
    private final ResponseDtoMapper<TransactionResponseDto, Transaction> responseDtoMapper;
    private final RequestDtoMapper<TransactionRequestDto, Transaction> requestDtoMapper;

    @PostMapping("/flat-fee/bank/{id}")
    public String makeFlatFeeTransaction(@RequestBody TransactionRequestDto transaction,
                                         @PathVariable Long id) {
        return service.transactionByFlatFee(requestDtoMapper.toModel(transaction), id);
    }

    @PostMapping("/percent-fee/bank/{id}")
    public String makePercentFeeTransaction(@RequestBody TransactionRequestDto transaction,
                                            @PathVariable Long id) {
        return service.transactionByPercentFee(requestDtoMapper.toModel(transaction), id);
    }

    @PostMapping("/withdrawal")
    public String withdrawalCashFromAccount(@RequestBody TransactionRequestDto transaction) {
        return service.withdrawalFromAccount(requestDtoMapper.toModel(transaction));
    }

    @PostMapping("/deposit")
    public String deposit(@RequestBody TransactionRequestDto transaction) {
        return service.depositToAccount(requestDtoMapper.toModel(transaction));
    }

    @GetMapping("/history-account/{id}")
    public List<TransactionResponseDto> getHistory(@PathVariable Long id) {
        return service.getHistory(id)
                .stream()
                .map(responseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
