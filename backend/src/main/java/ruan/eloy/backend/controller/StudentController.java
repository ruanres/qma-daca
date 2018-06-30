package ruan.eloy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.service.StudentService;

import java.util.List;
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
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = (List<Student>) studentService.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("{registration}/info")
    public ResponseEntity<String> getInfo(@PathVariable String registration, @RequestParam String prop) {
        String info = studentService.getStudentInfo(registration, prop);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("{registration}")
    public ResponseEntity<Student> findByRegistration(@PathVariable String registration) {
        Optional<Student> optionalStudent = studentService.getByRegistration(registration);
        if(optionalStudent.isPresent()) {
            return new ResponseEntity<>(optionalStudent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        studentService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
