package com.apricot.school.pdfGenerator;

import java.io.IOException;
import java.util.List;


import com.apricot.school.entity.StudentDetails;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

public class PDFGenerator {

    // List to hold all Students
    private List<StudentDetails> studentList;

    public void setStudentList(List<StudentDetails> studentList) {
        this.studentList = studentList;
    }

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("APRICOT SCHOOL - List of Students", fontTitle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        // Creating a table of 6 columns
        PdfPTable table = new PdfPTable(6);

        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3, 3});
        table.setSpacingBefore(3);

        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(CMYKColor.GRAY);
        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
        cell.setPhrase(new Phrase("Admission No.", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Student Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Father Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Mother Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Contact No.", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (StudentDetails student : studentList) {
            table.addCell(String.valueOf(student.getAdmissionNumber()));
            table.addCell(student.getSurName() + " " + student.getStudentName());
            table.addCell(student.getFatherName());
            table.addCell(student.getMotherName());
            table.addCell(student.getContactNumber());
            table.addCell(student.getAddress());
        }
        // Adding the created table to document
        document.add(table);

        // Closing the document
        document.close();

    }
}