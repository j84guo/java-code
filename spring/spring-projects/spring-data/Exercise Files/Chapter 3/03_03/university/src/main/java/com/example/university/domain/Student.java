package com.example.university.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private Integer studentId;

    @Column
    private boolean fullTime;

    @Column
    private Integer age;

    @Embedded
    private Person attendee;

    // default lazy load
    @OneToMany
    private List<Course> courses = new ArrayList<>();

    public Student(Person attendee, boolean fullTime, Integer age) {
        this.attendee = attendee;
        this.fullTime = fullTime;
        this.age = age;
        courses = new ArrayList<>();
    }

    protected Student() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Person getAttendee() {
        return attendee;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public Integer getAge() {
        return age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", " + attendee + ", fullTime=" + fullTime + ", age=" + age + "}\n";
    }
}
