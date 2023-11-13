package org.koreait.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Member {

    @Id
    private Long UserNo;

    private String email;

    private String password;

    private String userNm;

    private String mobile;

    private LocalDateTime regDt;

    private LocalDateTime modDt;
}
