package com.varma.user.service;

import com.varma.user.entity.Department;
import com.varma.user.entity.DepartmentService;
import com.varma.user.entity.User;
import com.varma.user.exception.UserNotFoundException;
import com.varma.user.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;


    public void saveUser(User user) {
        log.info("inside saveUser method userService");
        // Retrieve department data from DepartmentService
        departmentService.findDepartmentById(user.getDepartmentId());
        // Set the departmentId of the user

        userRepository.save(user);
    }


    public User fetchTheUser(Long userId) {
        log.info("inside fetchTheUser method userService");
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Department not found"));
    }
    }
