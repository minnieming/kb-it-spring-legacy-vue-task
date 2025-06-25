package org.scoula.security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

    private String username;
    private String password;

    private String email;
    private Date regDate;
    private Date updateDate;

    private List<AuthVO> authList; // 권할이 있는 테이블과 join 하기 위해서.
}
