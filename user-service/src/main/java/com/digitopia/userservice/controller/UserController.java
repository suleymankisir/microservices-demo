package com.digitopia.userservice.controller;

import com.digitopia.userservice.dto.UserRequest;
import com.digitopia.userservice.model.User;
import com.digitopia.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest){
        return ResponseEntity.ofNullable(this.userService.save(userRequest));
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ofNullable(this.userService.getAll());
    }

}
