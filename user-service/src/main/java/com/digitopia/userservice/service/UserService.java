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
        if(isEmailUnique(userRequest.email()) && isFullNameValid(userRequest.fullName())){
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
        } else if (!isEmailUnique(userRequest.email())) {
            throw new IllegalArgumentException("This email address is already in use, please try another email address.");
        }
        throw new IllegalArgumentException("Unexpected values were sent during registration. Your request has been rejected.");

    }
    public List<User> getAll() {
        return this.userRepository.findAll();
    }
    private boolean isEmailUnique(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }
    private boolean isFullNameValid(String fullName) {
        return fullName.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ ]+$");
    }
    private String normalizeFullName(String fullName) {
        return fullName.trim().toLowerCase()
                .replace("ı", "i")
                .replace("ö", "o")
                .replace("ü", "u")
                .replace("ş", "s")
                .replace("ğ", "g")
                .replace("ç", "c")
                .replace(" ", "")
                .replaceAll("[^a-z]", "");
    }

}
