package com.apricot.school.controller;

import com.apricot.school.entity.StudentDetails;
import com.apricot.school.pdfGenerator.PDFGenerator;
import com.apricot.school.service.StudentDetailsService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class StudentDetailsController {

    @Autowired
    StudentDetailsService service;

    @PostMapping("/add/student")
    public String addStudent(@RequestBody StudentDetails student) {
        return service.addStudent(student);
    }

    @DeleteMapping("/delete/student/{admissionNumber}")
    public String deleteStudent(@PathVariable("admissionNumber") Integer admissionNumber) {
        return service.deleteStudent(admissionNumber);
    }

    @PutMapping("/update/student/{admissionNumber}")
    public String updateStudent(@PathVariable("admissionNumber") Integer admissionNumber, @RequestBody StudentDetails student) {
        return service.updateStudent(admissionNumber, student);
    }

    @GetMapping("students/pdf")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=Apricot-school " + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<StudentDetails> studentList = service.findAllStudents();

        PDFGenerator generator = new PDFGenerator();
        generator.setStudentList(studentList);
        generator.generate(response);
    }

}
