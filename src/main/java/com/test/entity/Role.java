package com.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by intern1 on 5/1/2017.
 */

@Entity
@Table(name="Role")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
