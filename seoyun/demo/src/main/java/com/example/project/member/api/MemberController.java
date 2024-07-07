package com.example.project.member.api;

import com.example.project.member.api.dto.MemberResponseDto;
import com.example.project.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // create
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createMember(
            @RequestPart("name") String name,
        @RequestPart("email") String email) throws IOException {
        return ResponseEntity.ok().build();
    }

    // read
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> members = memberService.findAllMember();
        return ResponseEntity.ok(members);
    }

    // update
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestPart("name") String name,
            @RequestPart("email") String email) throws IOException {
        memberService.updateMember(memberId, name, email);
        return ResponseEntity.ok().build();
    }


    // delete
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}
