package com.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by intern1 on 5/4/2017.
 */
@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String username;
    private List roles;
}