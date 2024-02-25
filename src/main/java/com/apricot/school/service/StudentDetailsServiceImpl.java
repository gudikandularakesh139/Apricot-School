package com.apricot.school.service;

import com.apricot.school.entity.StudentDetails;
import com.apricot.school.repo.StudentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {

    @Autowired
    StudentDetailsRepo repo;

    @Override
    public String addStudent(StudentDetails student) {
        try {
            repo.save(student);
        } catch (Exception e) {
            return "Something went wrong while saving the data with error: " + e;
        }
        return "Saved Student Data";
    }

    @Override
    public List<StudentDetails> findAllStudents() {
        return repo.findAll();
    }

    @Override
    public String deleteStudent(Integer admissionNumber) {
        try {
            repo.deleteById(admissionNumber);
        } catch (Exception e) {
            return "Something went wrong while deleting the data with error: " + e;
        }
        return "Deleted Student Data Successfully...";
    }

    @Override
    public String updateStudent(Integer admissionNumber, StudentDetails student) {
        try {
            Optional<StudentDetails> optionalDetails = repo.findById(admissionNumber);

            if (optionalDetails.isPresent()) {
                StudentDetails existingStudent = optionalDetails.get();
                existingStudent.setSurName(student.getSurName());
                existingStudent.setStudentName(student.getStudentName());
                existingStudent.setFatherName(student.getFatherName());
                existingStudent.setMotherName(student.getMotherName());
                existingStudent.setContactNumber(student.getContactNumber());
                existingStudent.setAddress(student.getAddress());
                repo.save(existingStudent);
                return "Updated Student Data Successfully...";
            } else {
                return "Student with admission number " + admissionNumber + " not found.";
            }
        } catch (Exception e) {
            return "Something went wrong while updating the data with error: " + e.getMessage();
        }
    }
}
