package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_fileinfo_gid", columnList = "gid"),
        @Index(name = "idx_fileinfo_gid_location", columnList = "gid,location")
})
public class FileInfo extends BaseMember {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 45, nullable = false)
    private String gid = UUID.randomUUID().toString();
    //그룹 아이디
    // 랜덤 UUID를 생성해주는 편의기능

    @Column(length = 45)
    private String location;
    // 파일이 올라가는 위치

    @Column(length = 100, nullable = false)
    private String fileName;
    // 원래 파일명 -> id로 바꿔서 올림

    @Column(length = 45)
    private String extension;
    // 확장자

    @Column(length = 45)
    private String fileType;


    private boolean done;
    // 파일이 업로드 완료가 되었는지 여부

    @Transient
    private String filePath;
    // 실제 서버 업로드 경로

    @Transient
    private String fileUrl;
    // 서버 접속 URL

    @Transient
    private List<String> thumbPath; // 썸내일 이미지 경로

    @Transient
    private List<String> thumbUrl; // 썸네일 이미지 접속 UL
}
