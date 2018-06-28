package ruan.eloy.backend.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "registration"})})
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 9, max = 9)
    private String registration;

    @NotBlank
    @Size(min = 5, max = 5)
    private String courseCode;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Size(min = 10, max = 11)
    private String phone;

    @Min(0) @Max(5)
    private Integer rating = 5;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Tutor> tutors;

    public Student(String registration, String courseCode, String name, String email, String password, String phone) {
        this.registration = registration;
        this.courseCode = courseCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.tutors = new HashSet<>();
    }

    public Student() { }

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

    public Set<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
        this.tutors = tutors;
    }

    public void addTutor(Tutor tutor) {
        this.tutors.add(tutor);
    }

    public String getAttributeValue(StudentPublicAttrib attribute) {
        switch (attribute) {
            case NAME:
                return getName();
            case EMAIL:
                return getEmail();
            case COURSECODE:
                return getCourseCode();
            case PHONE:
                return getPhone();
            case RATING:
                return getRating().toString();
            default:
                return "";
        }
    }
}
