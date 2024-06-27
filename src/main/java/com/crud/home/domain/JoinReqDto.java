package com.crud.home.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinReqDto {

    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private String gender;

}
