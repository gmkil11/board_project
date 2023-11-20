package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.koreait.commons.constants.MemberType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // 보드데이터에 정의 되어있음 , cascade.REMOVE -> 자식이 삭제가 되고 부모가 삭제되게 함
    private List<BoardData> items = new ArrayList<>();


    /*
    @Transient
    private String tmpData;


    @Temporal(TemporalType.DATE)
    private Date date;
    */
}
