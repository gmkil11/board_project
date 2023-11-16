package org.koreait.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@IdClass(BoardViewId.class)
public class BoardView  {
    @Id
    private Long seq;

    @Id
    @Column(name = "_uid")
    private Integer uid;
}
