package com.likelion.oauth2test.feed.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.oauth2test.feed.controller.dto.request.UploadImageRequestDto;
import com.likelion.oauth2test.feed.controller.dto.response.FeedListResponse;
import com.likelion.oauth2test.feed.controller.dto.response.FeedResponse;
import com.likelion.oauth2test.feed.service.FeedService;
import com.likelion.oauth2test.global.exception.model.BaseResponse;
import com.likelion.oauth2test.global.exception.model.Success;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
	private final FeedService feedService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<FeedResponse> uploadImage(UploadImageRequestDto uploadImageRequestDto, @AuthenticationPrincipal String userId){
		return BaseResponse.success(Success.POST_SAVE_SUCCESS,feedService.uploadImage(uploadImageRequestDto, userId));
	}

	@GetMapping
	public BaseResponse<FeedListResponse> getAllFeedsOfUser(@AuthenticationPrincipal String userId){
		return BaseResponse.success(Success.GET_SUCCESS, feedService.getAllFeedsOfUser(userId));
	}
}
