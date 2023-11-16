package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.commons.constants.MemberType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_member_userNm", columnList = "userNm"),
        @Index(name = "idx_member_mobile", columnList = "mobile")
})
public class Member extends Base {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserNo;

    @Column(unique = true, nullable = false, length = 65)
    private String email;

    @Column(name="pw", length = 65, nullable = false)
    private String password;

    @Column(nullable = false, length = 45)
    private String userNm;

    @Column(length = 11)
    private String mobile;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType mtype = MemberType.USER;




    /*
    @Transient
    private String tmpData;


    @Temporal(TemporalType.DATE)
    private Date date;
    */
}
