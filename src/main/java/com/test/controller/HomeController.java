package com.test.controller;


import com.test.entity.Admin;
import com.test.entity.AdminRole;
import com.test.service.AdminRoleService;
import com.test.service.HomeService;
import com.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.service.AdminService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by intern1 on 4/30/2017.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    HomeService homeService;

    @GetMapping()
    public String test(Model model){

        model.addAttribute("list", homeService.homeList());
        model.addAttribute("roleList", roleService.getAllRole());
        System.out.println("Spring working!!");
        return "index";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }
}
