package com.sirius.challenge.backend.dtos;

import com.sirius.challenge.backend.security.models.Role;
import com.sirius.challenge.backend.security.models.User;

import java.io.Serializable;

public class CurrentUserDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Role role;

    public CurrentUserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }


}
