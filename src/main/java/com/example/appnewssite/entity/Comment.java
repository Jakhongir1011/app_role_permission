package com.example.appnewssite.entity;


import com.example.appnewssite.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractEntity {

    @Column(nullable = false,columnDefinition = "text")// bu ma'lumotlar omborida text bulsin deymiz
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
