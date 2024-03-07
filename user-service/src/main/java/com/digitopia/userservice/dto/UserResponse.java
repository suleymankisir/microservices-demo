package com.digitopia.userservice.dto;

import java.util.UUID;

public record UserResponse(

        UUID id,
        String fullName,
        String email,
        String normalizedName
) {
}
