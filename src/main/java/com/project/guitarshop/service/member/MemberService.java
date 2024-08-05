package com.project.guitarshop.service.member;

import com.project.guitarshop.dto.member.MemberRequest.*;
import com.project.guitarshop.dto.member.MemberResponse.*;

public interface MemberService {

    JoinResponse join(JoinRequest request);

    UpdateInfoResponse updateInfo(UpdateInfoRequest request);

    void updatePassword(UpdatePasswordRequest request);

    LoginResponse login(String loginEmail, String password);

    void delete();
}
