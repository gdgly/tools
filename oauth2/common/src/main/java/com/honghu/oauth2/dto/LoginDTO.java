package com.honghu.oauth2.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;

    private String password;

    private String clientId;

}
