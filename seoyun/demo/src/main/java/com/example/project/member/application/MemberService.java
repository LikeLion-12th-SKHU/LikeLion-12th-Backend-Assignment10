package com.example.project.member.application;

import com.example.project.member.api.dto.MemberResponseDto;
import com.example.project.member.domain.Member;
import com.example.project.member.domain.repository.MemberRepository;
import com.example.project.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

    // create
    @Transactional
    public void createMember(String name, String email) throws IOException {
        Member member = Member.builder()
                .name(name)
                .email(email)
                .build();
        memberRepository.save(member);
    }
    // read
    public List<MemberResponseDto> findAllMember() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for (Member member : memberList) {
            memberResponseDtoList.add(MemberResponseDto.of(member.getId(), member.getName(), member.getEmail()));
        }
        return memberResponseDtoList;
    }

    // update
    @Transactional
    public void updateMember(Long id, String name, String email) throws IOException {
       Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));

        member.updateMember(name, email);
        memberRepository.save(member);
    }
    // delete
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));
        memberRepository.delete(member);
    }

}
