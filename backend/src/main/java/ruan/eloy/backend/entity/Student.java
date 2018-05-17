package ruan.eloy.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Student {

    @Id
    @NotEmpty
    @Size(min=8, max = 8)
    private String registration;

    @NotEmpty
    @Size(min=8, max = 8)
    private String courseCode;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 10, max = 11)
    private String phone;

    @NotNull
    @Min(0) @Max(5)
    private Integer rating = 5;

    public Student(@NotEmpty @Size(min = 8, max = 8) String registration, @NotEmpty @Size(min = 8, max = 8) String courseCode, @NotEmpty String name, @NotEmpty @Email String email, @Size(min = 10, max = 11) String phone) {
        this.registration = registration;
        this.courseCode = courseCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Student() {
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
}
