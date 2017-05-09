package com.test.dto;

import com.test.entity.Admin;
import com.test.service.AdminService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by intern1 on 5/3/2017.
 */
@Getter
@Setter
public class FormSubmitDTO {

    private Admin admin;
    private Long[] roles;
    private Boolean edit;
    private String profilePic;

}
