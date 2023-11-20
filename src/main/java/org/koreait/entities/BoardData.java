package org.koreait.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardData extends BaseMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @Column(length = 100, nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userNo")
    private Member member;

}
