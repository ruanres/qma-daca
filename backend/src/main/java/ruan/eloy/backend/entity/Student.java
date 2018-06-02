package ruan.eloy.backend.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "registration"})
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min=9, max = 9, message = "registration must be 9 characters long")
    private String registration;

    @NotEmpty
    @Size(min=5, max = 5, message = "courseCode must be 5 characters long")
    private String courseCode;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Size(min = 10, max = 11, message = "phone number must be of size 10 or 11")
    private String phone;

    @Value("${student.initialRating}")
    @NotNull
    @Min(0) @Max(5)
    private Integer rating;

    public Student(String registration, String courseCode, String name, String email, String password, String phone) {
        this.registration = registration;
        this.courseCode = courseCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Student() {

    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
