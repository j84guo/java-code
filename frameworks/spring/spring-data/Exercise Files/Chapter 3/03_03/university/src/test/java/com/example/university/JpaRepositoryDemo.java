package com.example.university;

import com.example.university.domain.Department;
import com.example.university.repo.DepartmentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaRepositoryDemo {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void runJpaRepositoryMethods() {

        // changes are stored in memory until flushed
        departmentRepository.save(new Department("Humanities"));
        departmentRepository.flush();

        // save and flush can be combined
        departmentRepository.saveAndFlush(new Department("Fine Arts"));

        // no flush
        departmentRepository.save(new Department("Social Science"));

        System.out.println("\n*************3 Departments*************");
        departmentRepository.findAll().forEach(System.out::println);

        departmentRepository.deleteInBatch(departmentRepository.findAll().subList(0,1));

        System.out.println("\n*************1 Less Departments*************");
        departmentRepository.findAll().forEach(System.out::println);
        departmentRepository.deleteAllInBatch();

        System.out.println("\n*************Zero Departments*************");
        departmentRepository.findAll().forEach(System.out::println);

    }

    @Before
    @After
    public void banner() {
        System.out.println("\n\n-------------------------------------------------" + "-------------------------------------\n");
    }
}
