package com.digitopia.userservice.controller;

import com.digitopia.userservice.dto.UserRequest;
import com.digitopia.userservice.mapper.UserControllerMapper;
import com.digitopia.userservice.model.User;
import com.digitopia.userservice.service.UserService;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest){
        this.userService.save(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.findByEmail(userRequest.email()));
    }
    @GetMapping("user/find")
    public ResponseEntity<?> findById(@RequestParam("id") UUID id){
        var user = this.userService.findById(id);
        var userResponse = UserControllerMapper.convertToResponseUser(user);
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping("user/update")
    public ResponseEntity<?> updateByUser(@RequestParam("email") String email,@RequestBody UserRequest userRequest){
        var user = this.userService.updateByUser(email,userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/find/{normalizedName}")
    public ResponseEntity<?> getAll(@PathVariable("normalizedName") String normalizedName){
        return ResponseEntity.ok(this.userService.getAll(normalizedName));
    }

    @DeleteMapping("user/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("email") String email){
        return ResponseEntity.ok(this.userService.deleteByEmail(email));
    }

}
