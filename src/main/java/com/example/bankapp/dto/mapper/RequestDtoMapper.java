package com.example.bankapp.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
