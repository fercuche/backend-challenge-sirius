package com.sirius.challenge.backend.dtos;

import com.sirius.challenge.backend.security.models.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserStatsDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer emailDailyCount;
    private final LocalDate lastResetDay;

    public UserStatsDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.emailDailyCount = user.getEmailDailyCount();
        this.lastResetDay = user.getLastResetDay();
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

    public Integer getEmailDailyCount() {
        return emailDailyCount;
    }

    public LocalDate getLastResetDay() {
        return lastResetDay;
    }
}
