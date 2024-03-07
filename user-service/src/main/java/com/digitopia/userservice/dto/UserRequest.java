package com.digitopia.userservice.dto;

import java.util.UUID;

public record UserRequest(
        String fullName,
        String email,
        String normalizedName
) {
}
