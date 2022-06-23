package com.example.appnewssite.entity;

import com.example.appnewssite.entity.enums.Permission;
import com.example.appnewssite.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection //ManyToMany for enum
    private List<Permission> permission;

    @Column(length = 500)
    private String description;

}
