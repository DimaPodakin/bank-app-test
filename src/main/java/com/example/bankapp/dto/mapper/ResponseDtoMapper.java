package com.example.bankapp.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
