package com.pedro.users.infraestructure.in.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.domain1.users.domain.model.User;
import com.domain1.users.infraestructure.in.dtos.UserDTO;
import com.domain1.users.infraestructure.in.dtos.UserInDTO;
import com.domain1.users.infraestructure.in.mapper.UserDtoMapper;

public class UserDtoMapperTest {

    private final UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);

    @Test
    @DisplayName("should map User to UserDTO")
    void shouldMapUserToUserDTO() {

        User user = new User(1L, "Pedro", "Sotelo", "pedro@gmail.com", "987654321");

        
        UserDTO dto = mapper.userToUserDto(user);
   
        assertThat(dto.id()).isEqualTo(user.getId());
        assertThat(dto.name()).isEqualTo(user.getName());
        assertThat(dto.lastname()).isEqualTo(user.getLastname());
        assertThat(dto.email()).isEqualTo(user.getEmail());
        assertThat(dto.phonenumber()).isEqualTo(user.getPhonenumber());
    }

    @Test
    @DisplayName("should map UserInDTO to User")
    void shouldMapUserInDTOToUser() {
        
        UserInDTO dto = new UserInDTO("Pedro", "Sotelo", "pedro@gmail.com", "987654321");

        User user = mapper.userInDtoTOUser(dto);

        assertThat(user.getId()).isNull();
        assertThat(user.getName()).isEqualTo(dto.name());
        assertThat(user.getLastname()).isEqualTo(dto.lastname());
        assertThat(user.getEmail()).isEqualTo(dto.email());
        assertThat(user.getPhonenumber()).isEqualTo(dto.phonenumber());
    }
}
