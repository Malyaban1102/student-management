package com.example.student_management.Service;

import com.example.student_management.Dto.LoginRequest;
import com.example.student_management.Entity.Student;
import com.example.student_management.Repository.StudentRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl {
    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerStudent(@Valid Student student) {
        String password=student.getPassword();
        if(password.length()<8){
            throw new ValidationException("Password must be at least 8 characters");
        }
        student.setPassword(passwordEncoder.encode(password));
        studentRepository.save(student);
        return "Student registered successfully";
    }

    public String loginStudent(@Valid LoginRequest loginRequest) {
        Student existingStudent=studentRepository.findByEmail(loginRequest.getEmail());
        if(existingStudent==null){
            throw new ValidationException("Student not found");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),existingStudent.getPassword())){
            throw new ValidationException("Wrong password");
        }
        return jwtService.generateToken(existingStudent.getUsername());
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public String updateStudent(Long id, @Valid Student student) {
        Optional<Student> existingStudentOpt=studentRepository.findById(id);
        if(existingStudentOpt.isEmpty()){
            throw new ValidationException("Student not found");
        }
        Student existingStudent=existingStudentOpt.get();
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        if(student.getPassword()!=null && student.getPassword().length()<8){
            existingStudent.setPassword(passwordEncoder.encode(student.getPassword()));
        }
        existingStudent.setDepartment(student.getDepartment());
        studentRepository.save(existingStudent);
        return "Student updated successfully";
    }

    public String deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new ValidationException("Student not found");
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
