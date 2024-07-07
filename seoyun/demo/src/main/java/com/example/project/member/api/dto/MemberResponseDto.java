package com.example.project.member.api.dto;

public record MemberResponseDto(
        Long id,
        String name,
        String email
) {
    public static MemberResponseDto of(Long id, String name, String email) {
        return new MemberResponseDto(id, name, email);
    }
}
