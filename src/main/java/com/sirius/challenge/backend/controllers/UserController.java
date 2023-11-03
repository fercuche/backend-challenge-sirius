package com.sirius.challenge.backend.controllers;


import com.sirius.challenge.backend.documentation.IUserController;
import com.sirius.challenge.backend.dtos.CurrentUserDto;
import com.sirius.challenge.backend.dtos.UserStatsDto;
import com.sirius.challenge.backend.security.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/users")
public class UserController implements IUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(){
        List<UserStatsDto> userStatsDtoList = userRepository.findAll().stream()
                .filter(user -> user.getEmailDailyCount()>0)
                .map(UserStatsDto::new).collect(toList());
        if (userStatsDtoList.isEmpty()){
            return new ResponseEntity<>("The list is empty", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(userStatsDtoList);
    }
}
