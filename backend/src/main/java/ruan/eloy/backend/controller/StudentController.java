package ruan.eloy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.service.StudentService;

import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public Iterable<Student> findAll() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Student> findById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping()
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        studentService.removeById(id);
    }

}
