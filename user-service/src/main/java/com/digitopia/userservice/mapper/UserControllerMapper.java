package com.digitopia.userservice.mapper;

import com.digitopia.userservice.dto.UserResponse;
import com.digitopia.userservice.model.User;

import java.util.Locale;

public class UserControllerMapper {
    public static UserResponse convertToResponseUser(User user){
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getNormalizedName());
    }
}
