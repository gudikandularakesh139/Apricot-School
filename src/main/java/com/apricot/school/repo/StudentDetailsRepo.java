package com.apricot.school.repo;

import com.apricot.school.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepo extends JpaRepository<StudentDetails, Integer> {
}
