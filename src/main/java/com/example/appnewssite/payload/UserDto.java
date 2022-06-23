package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "fullName not available")
    private String fullName;

    @NotNull(message = "userName not available")
    private String userName;

    @NotNull(message = "password not available")
    private String password;

    @NotNull(message = "RoleId not available")
    private Integer RoleId;
}
