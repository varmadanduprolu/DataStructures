package com.varma.user.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentService {

    @PostMapping("departments/new")
    public void saveDepartment(@RequestBody Department department);

    @GetMapping("departments/id/{departmentId}")
    Department findDepartmentById(@PathVariable("departmentId") Long departmentId);
}

