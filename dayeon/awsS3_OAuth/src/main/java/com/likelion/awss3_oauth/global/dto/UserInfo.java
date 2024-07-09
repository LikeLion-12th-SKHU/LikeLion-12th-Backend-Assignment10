package com.likelion.awss3_oauth.global.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UserInfo {
    private String id;
    private String email;

    @SerializedName("verified_email") // 키 값으로 사용하고 싶을 때(키에 넘기고 싶은 정보)
    private Boolean verifiedEmail;

    private String name;

    @SerializedName("given_name")
    private String givenName;

    private String userImage;
    private String content;

    @SerializedName("user_image")
    private String image;
}
