package ruan.eloy.backend.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.eloy.backend.dto.StudentResponse;
import ruan.eloy.backend.exception.StudentNotFoundException;
import ruan.eloy.backend.entity.Role;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.repository.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private ModelMapper modelMapper;

    private TypeMap<Student, StudentResponse> studentToDTO;

    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        setStudentMapping();
    }

    private void setStudentMapping() {
        studentToDTO = this.modelMapper.createTypeMap(Student.class, StudentResponse.class);
    }

    public Student create(String registration, String courseCode,
                          String name, String email, String password, String phone) {

        Student student = new Student(registration, courseCode, name, email, password, phone);
        student.setRoles(Collections.singleton(Role.ROLE_STUDENT));
        return studentRepository.save(student);
    }

    public List<StudentResponse> findAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students.stream()
                .map(student -> studentToDTO.map(student))
                .collect(Collectors.toList());
    }

    public StudentResponse getByRegistration(String registration) {
        Student student = studentRepository.findByRegistration(registration)
                .orElseThrow(() -> new StudentNotFoundException());

        return studentToDTO.map(student);
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
