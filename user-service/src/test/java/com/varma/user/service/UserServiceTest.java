package com.varma.user.service;

import com.varma.user.entity.Department;
import com.varma.user.entity.DepartmentService;
import com.varma.user.entity.User;
import com.varma.user.exception.UserNotFoundException;
import com.varma.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("Should throw an exception when the department id does not exist")
    void saveUserWhenDepartmentIdDoesNotExistThenThrowException() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setDepartmentId(100L);

         when(departmentService.findDepartmentById(user.getDepartmentId())).thenThrow(new UserNotFoundException("Department not found"));

        assertThrows(UserNotFoundException.class, () -> userService.saveUser(user));

        verify(departmentService, times(1)).findDepartmentById(user.getDepartmentId());
        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName("Should save the user and not throw any exception when the department id exists")
    void saveUserWhenDepartmentIdExists() {
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setDepartmentId(1L);

        when(departmentService.findDepartmentById(user.getDepartmentId())).thenReturn(new Department(1L, "IT Department", "123 Main St", "IT"));

        userService.saveUser(user);

        verify(departmentService, times(1)).findDepartmentById(user.getDepartmentId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should throw an exception when the user id does not exist")
    void fetchTheUserWhenUserIdDoesNotExistThenThrowException() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.fetchTheUser(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should return the user when the user id exists")
    void fetchTheUserWhenUserIdExists() {
        Long userId = 1L;
        User user = new User(userId, "John", "Doe", "john.doe@example.com", 1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User fetchedUser = userService.fetchTheUser(userId);

        assertEquals(user, fetchedUser);
        verify(userRepository, times(1)).findById(userId);
    }
}