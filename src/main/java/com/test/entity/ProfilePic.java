package com.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by intern1 on 5/7/2017.
 */

@Entity
@Table(name = "profile_pic")
@Setter
@Getter
public class ProfilePic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @JoinColumn(name = "admin_id")
    @OneToOne
    private Admin admin;
}
