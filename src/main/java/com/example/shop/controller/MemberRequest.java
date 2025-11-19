package com.example.shop.controller;

public record MemberRequest(
        String email,
        String name,
        String password,
        String phone,
        String saltKey,
        String flag
) {
}
