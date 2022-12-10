package com.example.springbootjpatuto1.service;

import com.example.springbootjpatuto1.domain.user.Member;
import com.example.springbootjpatuto1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Long update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        if (member == null) {
            return null;
        }

        member.setName(name);

        return id;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(long memberId) {
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.findByName(member.getName()).isEmpty()) {
            return;
        }

        throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
