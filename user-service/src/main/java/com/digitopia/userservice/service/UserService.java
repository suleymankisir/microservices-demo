package com.digitopia.userservice.service;

import com.digitopia.userservice.dto.UserRequest;
import com.digitopia.userservice.dto.UserResponse;
import com.digitopia.userservice.exception.UserNotFoundException;
import com.digitopia.userservice.model.Role;
import com.digitopia.userservice.model.Status;
import com.digitopia.userservice.model.User;
import com.digitopia.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(UserRequest userRequest){
        if(isEmailUnique(userRequest.email()) && isFullNameValid(userRequest.fullName())){
        User user = User.userBuilder()
                .email(userRequest.email().toLowerCase())
                .fullName(userRequest.fullName())
                .normalizedName(normalizeFullName(userRequest.normalizedName()))
                .role(Role.USER)
                .status(Status.PENDING)
                .created(LocalDateTime.now())
                .build();
        this.userRepository.save(user);
        return this.userRepository.findByEmail(userRequest.email()).orElseThrow();
        }
        else if (!isEmailUnique(userRequest.email())) {
            throw new IllegalArgumentException("This email address is already in use, please try another email address.");
        }
        throw new IllegalArgumentException("Unexpected values were sent during registration. Your request has been rejected.");

    }
    public List<User> getAll(String normalizedName) {
        return this.userRepository.findByNormalizedNameContainingIgnoreCase(normalizedName);
    }
    public User findByEmail(String email){
        if (email != null && !email.isEmpty() ){
            return this.userRepository.findByEmail(email).orElseThrow(() ->
                    new UserNotFoundException("The user (mail: %s) you are looking for could not be found in the system."
                            ,email
                    )
            );

        }
        throw new IllegalArgumentException("Your request has been rejected.");
    }
    public User findById(UUID id){
        if (id != null){
            return this.userRepository.findById(id).orElseThrow(() ->
                    new UserNotFoundException("The user (id: %s) you are looking for could not be found in the system."
                            ,id.toString()
                    )
            );

        }
        throw new IllegalArgumentException("Your request has been rejected.");
    }
    public User updateByUser(String email, UserRequest userRequest) {
        if ((email != null && !email.isEmpty())&& userRequest != null){
            User user = this.findByEmail(email);
            if(updateUserControl(user,userRequest)){
                User updateUser = updateUser(user,userRequest);
                User newUser = this.userRepository.save(updateUser);
                return newUser;
            }
        }
        throw new IllegalArgumentException("Your request has been rejected.");
    }

    public User deleteByEmail(String email){
        User user = this.findByEmail(email);
        if(email != null){
        this.userRepository.deleteById(user.getId());
        return user;
        }
        throw new IllegalArgumentException("Your request has been rejected.");

    }

    private User updateUser(User user, UserRequest userRequest) {
        final User account = this.findByEmail(user.getEmail());

        return User.userBuilder()
                .id(user.getId())
                .createdBy(user.getId())
                .updatedBy(user.getId())
                .fullName(userRequest.fullName())
                .normalizedName(normalizeFullName(userRequest.normalizedName()))
                .status(Status.PENDING)
                .email(userRequest.email().toLowerCase())
                .role(user.getRole())
                .created(user.getCreated())
                .updated(LocalDateTime.now())
                .build();

    }

    private boolean updateUserControl(User user, UserRequest userRequest) {
        return user.getEmail().equalsIgnoreCase(userRequest.email()) || isEmailUnique(userRequest.email());
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
