package com.example.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

 @NotNull(message = "fullName not available")
 private String fullName;

 @NotNull(message = "userName not available")
 private String userName;

 @NotNull(message = "password not available")
 private String password;

 @NotNull(message = "prePassword not available")
 private String PrePassword;

}
