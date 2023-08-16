package com.varma.department.controller;


import com.varma.department.entity.Department;
import com.varma.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("new")
    public void saveDepartment(@RequestBody Department department){
        log.info("Inside saveDepartment method of department controller");
         departmentService.saveDepartment(department);
    }

    @GetMapping("id/{departmentId}")
    public Department findDepartmentById(@PathVariable("departmentId") Long departmentId){
        log.info("Inside findDepartmentById method of department service");
        return departmentService.findDepartmentById(departmentId);
    }

}
