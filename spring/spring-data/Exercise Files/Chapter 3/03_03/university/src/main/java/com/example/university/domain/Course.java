package com.example.university.domain;

// jpa specification annotations
import javax.persistence.*;

// jpa course entity, represents a table's row structure
@Entity
@Table(name = "COURSE")
public class Course {

    // auto-generated unique id
    @Id
    @GeneratedValue
    private Integer id;

    // table column
    @Column
    private String name;

    // many students map to a department
    @ManyToOne
    @JoinColumn
    private Department department;

    // constructors for insertion
    public Course(String name, Department department) {
        this.name = name;
        this.department = department;
    }
    protected Course() {
    }

    // data getters/setters
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    // debug print statement
    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", department=" + department.getName() + '}';
    }
}
