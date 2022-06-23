package com.example.appnewssite.entity.template;

import com.example.appnewssite.entity.User;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updateAt;

    @JoinColumn(updatable = false)// qachonki sizni tipiz oziz yaratgan class  tipda bolsa joinColumn boladi
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updateBy;

}



