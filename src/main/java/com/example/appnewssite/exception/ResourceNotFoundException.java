package com.example.appnewssite.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName; // role
    private String resourceField; // name;
    private Object object; // USER, ADMIN, 1 ,500

    public ResourceNotFoundException(Object object) {
        this.object = object;
    }
}
