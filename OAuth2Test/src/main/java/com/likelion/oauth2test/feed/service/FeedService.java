package com.likelion.oauth2test.feed.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.likelion.oauth2test.external.S3Service;
import com.likelion.oauth2test.feed.controller.dto.request.UploadImageRequestDto;
import com.likelion.oauth2test.feed.controller.dto.response.FeedListResponse;
import com.likelion.oauth2test.feed.controller.dto.response.FeedResponse;
import com.likelion.oauth2test.feed.domain.Feed;
import com.likelion.oauth2test.feed.domain.repository.FeedRepository;
import com.likelion.oauth2test.global.exception.ForbiddenException;
import com.likelion.oauth2test.global.exception.NotFoundException;
import com.likelion.oauth2test.global.exception.model.Error;
import com.likelion.oauth2test.user.domain.User;
import com.likelion.oauth2test.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedService {
	private final FeedRepository feedRepository;
	private final UserRepository userRepository;
	private final S3Service s3Service;

	@Transactional
	public FeedResponse uploadImage(UploadImageRequestDto uploadImageRequestDto, String userId) {
		User user = findUserInFeedService(userId);
		String imageUrl = s3Service.uploadImage(uploadImageRequestDto.feedImage(), "testimage");
		Feed feed = Feed.builder()
			.image(imageUrl)
			.content(uploadImageRequestDto.content())
			.title(uploadImageRequestDto.feedTitle())
			.user(user)
			.build();
		feedRepository.save(feed);
		return FeedResponse.from(feed);
	}

	public FeedListResponse getAllFeedsOfUser(String userId){
		User user = findUserInFeedService(userId);
		List<FeedResponse> feeds = feedRepository.findAllByUser(user)
			.stream()
			.map(FeedResponse::from)
			.collect(Collectors.toList());
		return FeedListResponse.from(feeds);
	}

	@Transactional
	public void deleteFeedOfUser(String userId, Long feedId){
		User user = findUserInFeedService(userId);
		Feed feed = feedRepository.findById(feedId).orElseThrow(
			() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
		);
		if (!hasPermission(user,feed)){
			throw new ForbiddenException(Error.FORBIDDEN_ERROR, Error.FORBIDDEN_ERROR.getMessage());
		}
		s3Service.deleteFile(feed.getImage());
		feedRepository.deleteById(feedId);
	}

	@Transactional
	public FeedResponse updateFeedImageOfUser(String userId, Long feedId, MultipartFile updateImage){
		User user = findUserInFeedService(userId);
		Feed feed = feedRepository.findById(feedId).orElseThrow(
			() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
		);
		if (!hasPermission(user,feed)){
			throw new ForbiddenException(Error.FORBIDDEN_ERROR, Error.FORBIDDEN_ERROR.getMessage());
		}
		String imageUrl = feed.getImage();
		if (updateImage != null && !updateImage.isEmpty()){
			if (imageUrl != null && !imageUrl.isEmpty()){
				s3Service.deleteFile(imageUrl);
			}
			imageUrl = s3Service.uploadImage(updateImage, "testimage");
		}
		feed.updateImage(imageUrl);
		return FeedResponse.from(feed);

	}

	private User findUserInFeedService(String userId){
		return userRepository.findById(Long.parseLong(userId))
			.orElseThrow(
				() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION,
					Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
			);
	}

	private boolean hasPermission(User user, Feed feed){
		return user.equals(feed.getUser());
	}

}
