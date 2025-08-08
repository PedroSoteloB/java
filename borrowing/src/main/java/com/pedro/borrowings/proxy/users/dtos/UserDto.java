package com.pedro.borrowings.proxy.users.dtos;

public record UserDto(
        Long id,
        String name,
        String lastname,
        String phonenumber,
        String email
) {}
