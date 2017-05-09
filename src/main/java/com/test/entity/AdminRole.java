package com.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by intern1 on 5/1/2017.
 */
@Entity
@Table(name = "admin_roles")
@Setter
@Getter
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "admin_id")
    @ManyToOne
    private Admin admin;

    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role role;

}
