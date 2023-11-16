package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class FileInfo extends BaseMember {

    @Id
    @GeneratedValue
    private Long id;

    //그룹 아이디
    private String gid;

    private String location;

    private String fileName;
}
