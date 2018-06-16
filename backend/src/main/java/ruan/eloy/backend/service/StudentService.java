package ruan.eloy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ruan.eloy.backend.Exceptions.AppException;
import ruan.eloy.backend.entity.Role;
import ruan.eloy.backend.entity.RoleName;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.StudentPublicAttrib;
import ruan.eloy.backend.repository.RoleRepository;
import ruan.eloy.backend.repository.StudentRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private RoleRepository roleRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    public Student create(String registration, String courseCode,
                          String name, String email, String password, String phone) {

        Student student = new Student(registration, courseCode, name, email, password, phone);

        Role role = roleRepository.findByName(RoleName.ROLE_STUDENT)
                .orElseThrow(() -> new AppException("Student role not set."));
        student.setRoles(Collections.singleton(role));

        return studentRepository.save(student);
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> getByRegistration(String registration) {
        return studentRepository.findByRegistration(registration);
    }

    public String getStudentInfo(String registration, String attribute) {
        Student student = getByRegistration(registration)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        try {
            attribute = attribute.trim().toUpperCase();
            StudentPublicAttrib attrib = StudentPublicAttrib.valueOf(attribute);
            return student.getAttributeValue(attrib);
        } catch (IllegalArgumentException e) {
            throw new AppException("Student info not found or not public");
        }
    }

    public void removeById(Long id) {
        studentRepository.deleteById(id);
    }

    public Boolean isRegistrationUnique(String registration) {
        return studentRepository.existsByRegistration(registration);
    }

    public Boolean isEmailUnique(String email) {
        return studentRepository.existsByEmail(email);
    }
}
