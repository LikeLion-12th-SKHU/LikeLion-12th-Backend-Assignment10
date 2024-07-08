package com.likelion.oauth2test.feed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@DeleteMapping("/{feedId}")
	public BaseResponse<String> deleteFeedOfUser(@AuthenticationPrincipal String userId, @PathVariable(name = "feedId") Long feedId){
		feedService.deleteFeedOfUser(userId, feedId);
		return BaseResponse.success(Success.POST_DELETE_SUCCESS, "삭제성공, id = :"+feedId);
	}

	@PatchMapping(value = "/{feedId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<FeedResponse> updateFeedImageOfUser(@AuthenticationPrincipal String userId, @PathVariable(name = "feedId") Long feedId, @RequestPart(name = "image")
		MultipartFile updateImage){
		return BaseResponse.success(Success.POST_SAVE_SUCCESS,feedService.updateFeedImageOfUser(userId, feedId, updateImage));
	}
}
