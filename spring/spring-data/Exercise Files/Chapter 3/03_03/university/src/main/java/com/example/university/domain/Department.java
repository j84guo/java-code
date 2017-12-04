package com.example.university.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    // many courses will map to this department
    // default behaviour is to lazy load courses, eager fetch loads courses at object creation
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    protected Department() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name='" + name + '\'' +
                ", courses=" + courses + '}';
    }
}
