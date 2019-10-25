package com.thamserios.notificationsserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String identifier;
    private String dateOfBirth;
    private String cellPhone;
    private String email;
    private String countryCode;

    public Person() {
    }

    public Person(final String id, final String firstName, final String lastName, final String identifier, final String dateOfBirth, final String cellPhone, final String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifier = identifier;
        this.dateOfBirth = dateOfBirth;
        this.cellPhone = cellPhone;
        this.email = email;
    }
}

