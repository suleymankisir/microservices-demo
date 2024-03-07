package com.digitopia.userservice.service;

import com.digitopia.userservice.dto.UserRequest;
import com.digitopia.userservice.dto.UserResponse;
import com.digitopia.userservice.model.Role;
import com.digitopia.userservice.model.Status;
import com.digitopia.userservice.model.User;
import com.digitopia.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(UserRequest userRequest){
        User user = User.userBuilder()
                .email(userRequest.email())
                .fullName(userRequest.fullName())
                .normalizedName(userRequest.normalizedName())
                .role(Role.USER)
                .status(Status.PENDING)
                .created(LocalDateTime.now())
                .build();
        this.userRepository.save(user);
        return this.userRepository.findByEmail(userRequest.email()).orElseThrow();
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
