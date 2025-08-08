package com.domain1.users.infraestructure.in;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.domain1.users.infraestructure.in.dtos.UserDTO;
import com.domain1.users.infraestructure.in.dtos.UserInDTO;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;


@RequestMapping("/users")
@Validated
public interface UserController {
   @GetMapping("/{id}")
    Mono<UserDTO> getById(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Mono<Void> create(@Valid @RequestBody UserInDTO userInDTO);


}
