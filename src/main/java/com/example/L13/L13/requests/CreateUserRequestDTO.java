package com.example.L13.L13.requests;


import com.example.L13.L13.models.UserInfo;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {

    @NotBlank
    String name;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String address;

    @NotNull
    Long phoneNumber;

    public UserInfo toUser(){
        return UserInfo.builder()
                .name(name)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }
}
