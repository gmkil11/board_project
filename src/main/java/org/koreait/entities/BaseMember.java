package org.koreait.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseMember extends Base {

    @CreatedBy
    @Column(updatable = false, length = 65)
    private String createdBy;

    @LastModifiedBy
    @Column(insertable = false, length = 65)
    private String modifiedBy;
}
