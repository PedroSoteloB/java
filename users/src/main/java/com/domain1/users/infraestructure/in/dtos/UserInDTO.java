package com.domain1.users.infraestructure.in.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserInDTO (
    @Size(min = 2, max=20)
    @NotNull
    String name,
    @Size(min = 2, max=20)
    @NotNull
    String lastname,
    @Email
    @NotNull
    String email,
    @NotNull
    String phonenumber) {

}
