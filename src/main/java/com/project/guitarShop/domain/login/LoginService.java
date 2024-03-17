package com.project.guitarShop.domain.login;

import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.domain.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public Member login(String memberId, String password) {
        return memberMapper.findByMemberId(memberId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}