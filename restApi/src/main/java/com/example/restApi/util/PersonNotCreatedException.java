package com.example.restApi.util;

public class PersonNotCreatedException extends RuntimeException {

    public PersonNotCreatedException(String msg) {
        super(msg);
    }
}
