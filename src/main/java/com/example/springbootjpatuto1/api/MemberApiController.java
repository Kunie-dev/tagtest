package com.example.springbootjpatuto1.api;

import com.example.springbootjpatuto1.domain.user.Member;
import com.example.springbootjpatuto1.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.name);
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v1/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable Long id,
            @RequestBody @Valid UpdateMemberRequest request
    ) {
        Long updateId = memberService.update(id, request.name);
        if (updateId == null) {
            return null;
        } else {
            Member member = memberService.findOne(updateId);
            return new UpdateMemberResponse(member.getId(), member.getName());
        }
    }

    @GetMapping("/api/v1/members")
    public BaseResponse<List<MemberDto>> getMembersV2() {
        List<Member> findMembers = memberService.findMembers();
        return new BaseResponse<>(findMembers.size(), findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList()));
    }

    @Data
    @AllArgsConstructor
    static class BaseResponse<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @Data
    static class CreateMemberRequest implements Serializable {
        @NotEmpty
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @Data
    static class UpdateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }
}
