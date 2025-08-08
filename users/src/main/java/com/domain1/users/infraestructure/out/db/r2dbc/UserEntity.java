package com.domain1.users.infraestructure.out.db.r2dbc;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Table(name="users")
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phonenumber;
}
