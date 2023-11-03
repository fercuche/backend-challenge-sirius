package com.sirius.challenge.backend.security.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer emailDailyCount;
    private LocalDate lastResetDay;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){
    }

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.emailDailyCount = 0;
        this.lastResetDay = LocalDate.now();
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Integer getEmailDailyCount() {
        return emailDailyCount;
    }

    public LocalDate getLastResetDay() {
        return lastResetDay;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmailDailyCount(Integer emailDailyCount) {
        this.emailDailyCount = emailDailyCount;
    }

    public void setLastResetDay(LocalDate lastResetDay) {
        this.lastResetDay = lastResetDay;
    }

    public void resetCounter(){
        this.lastResetDay = LocalDate.now();
        this.setEmailDailyCount(0);
    }

    public void mailSent(){
        if(!LocalDate.now().isEqual(this.lastResetDay)) {
            resetCounter();
        }
        this.emailDailyCount += 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
