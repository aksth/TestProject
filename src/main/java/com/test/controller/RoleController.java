package com.test.controller;

import com.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by intern1 on 5/3/2017.
 */
@RestController
@RequestMapping("/roles/")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value = "fetchAll")
    public ResponseEntity<List> fetchAll(){
        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }
}
