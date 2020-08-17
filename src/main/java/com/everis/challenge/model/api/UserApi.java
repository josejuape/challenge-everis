package com.everis.challenge.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserApi implements Serializable {
    private static final long serialVersionUID = 1;
    private String username;
    private String password;
}
