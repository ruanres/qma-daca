package ruan.eloy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.dto.TutorRequest;
import ruan.eloy.backend.security.CurrentUser;
import ruan.eloy.backend.security.StudentPrincipal;
import ruan.eloy.backend.service.TutorService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("tutor")
public class TutorController {

    private TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<Tutor> createOne(@RequestBody TutorRequest tutorRequest,
                                           @CurrentUser StudentPrincipal currentStudent) {
        Tutor tutor = tutorService.create(tutorRequest, currentStudent.getRegistration());
        return new ResponseEntity<>(tutor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> findAll() {
        List<Tutor> tutors = (List<Tutor>) tutorService.getAll();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @GetMapping("{registration}")
    public ResponseEntity<Set<Tutor>> findTutorsByRegistration(@PathVariable String registration) {
        Set<Tutor> tutors = tutorService.getTutorsByRegistration(registration);
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @DeleteMapping("{tutorId}")
    public ResponseEntity removeTutor(@PathVariable Long tutorId, @CurrentUser StudentPrincipal currentStudent) {
        tutorService.removeTutor(tutorId, currentStudent.getTutors());
        return new ResponseEntity(HttpStatus.OK);
    }
}
