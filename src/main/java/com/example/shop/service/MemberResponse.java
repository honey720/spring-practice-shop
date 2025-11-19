package com.example.shop.service;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberResponse(
        UUID id,
        String email,
        String name,
        String password,
        String phone,
        UUID regId,
        LocalDateTime regDt,
        UUID modifyId,
        LocalDateTime modifyDt,
        String saltKey,
        String flag
) {
}
