package ruan.eloy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.repository.StudentRepository;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(@Valid Student student) {
        return studentRepository.save(student);
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> getByEmail(String email) {
        return Optional.ofNullable(studentRepository.findByEmail(email));
    }

    public Optional<Student> getById(String id) {
        return studentRepository.findById(id);
    }

    public void removeById(String id) {
        studentRepository.deleteById(id);
    }

}
