package com.example.bankapp.dto.mapper;

import com.example.bankapp.dto.request.BankRequestDto;
import com.example.bankapp.dto.response.BankResponseDto;
import com.example.bankapp.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper implements RequestDtoMapper<BankRequestDto, Bank>,
        ResponseDtoMapper<BankResponseDto, Bank> {

    @Override
    public Bank toModel(BankRequestDto dto) {
        Bank bank = new Bank();
        bank.setNameBank(dto.getNameBank());
        bank.setFlatFee(dto.getFlatFee());
        bank.setPercentFee(dto.getPercentFee());
        bank.setTotalTransactionFeeAmount(dto.getTotalTransactionFeeAmount());
        bank.setTotalTransferAmount(dto.getTotalTransferAmount());
        return bank;
    }

    @Override
    public BankResponseDto toDto(Bank bank) {
        BankResponseDto dto = new BankResponseDto();
        dto.setId(bank.getId());
        dto.setNameBank(bank.getNameBank());
        dto.setFlatFee(bank.getFlatFee());
        dto.setPercentFee(bank.getPercentFee());
        dto.setTotalTransactionFeeAmount(bank.getTotalTransactionFeeAmount());
        dto.setTotalTransferAmount(bank.getTotalTransferAmount());
        return dto;
    }
}
