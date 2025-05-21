package uz.hojiakbar.newprogect.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    public Student() {

    }



    private Long id;
    private String name;

    private String surname;
    private Integer age;
    private Course course;

    public Student(Long id, String name, String surname, Integer age, Course course) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
