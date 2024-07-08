package com.likelion.oauth2test.feed.controller.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageRequestDto(
	MultipartFile feedImage,
	String feedTitle,
	String content



) {
}
