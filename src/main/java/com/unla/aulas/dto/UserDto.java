package com.unla.aulas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {
    private String userName;
    private String password;
    private String name;
    private String surname;
    private String documentType;
    private String document;
    private String email;
}
