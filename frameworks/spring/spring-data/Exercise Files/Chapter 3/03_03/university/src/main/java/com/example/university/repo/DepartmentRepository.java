package com.example.university.repo;

import com.example.university.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

// spring's jpa repository defines common jpa methods
// the implementation is automatically provided as well
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
