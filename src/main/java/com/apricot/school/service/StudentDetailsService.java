package com.apricot.school.service;

import com.apricot.school.entity.StudentDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentDetailsService {

    public String addStudent(StudentDetails student);

    public List<StudentDetails> findAllStudents();

    String deleteStudent(Integer admissionNumber);

    String updateStudent(Integer admissionNumber, StudentDetails student);
}
