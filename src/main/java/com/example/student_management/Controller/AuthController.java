package com.example.student_management.Controller;

import com.example.student_management.Dto.LoginRequest;
import com.example.student_management.Entity.Student;
import com.example.student_management.Service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody Student student) {
        String response=studentServiceImpl.registerStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String response=studentServiceImpl.loginStudent(loginRequest);
        return ResponseEntity.ok(response);
    }
}
