package ruan.eloy.backend.dto;

import ruan.eloy.backend.entity.Tutor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentResponse {

    private Long id;
    private String registration;
    private String courseCode;
    private String name;
    private String email;
    private String phone;
    private Integer rating;
    private List<Long> tutors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Long> getTutors() {
        return tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
       this.tutors = tutors.stream()
               .map(Tutor::getId)
               .collect(Collectors.toList());
    }
}
