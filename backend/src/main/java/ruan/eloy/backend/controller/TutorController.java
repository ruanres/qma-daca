package ruan.eloy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruan.eloy.backend.dto.TutorResponse;
import ruan.eloy.backend.dto.TutorRequest;
import ruan.eloy.backend.security.CurrentUser;
import ruan.eloy.backend.security.StudentPrincipal;
import ruan.eloy.backend.service.TutorService;

import java.util.List;

@RestController
@RequestMapping("tutor")
public class TutorController {

    private TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<TutorResponse> createOne(@RequestBody TutorRequest tutorRequest,
                                           @CurrentUser StudentPrincipal currentStudent) {
        TutorResponse tutor = tutorService.create(tutorRequest, currentStudent.getRegistration());
        return new ResponseEntity<>(tutor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TutorResponse>> findAll() {
        List<TutorResponse> tutors = tutorService.getAll();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @GetMapping("{registration}")
    public ResponseEntity<List<TutorResponse>> findTutorsByRegistration(@PathVariable String registration) {
        List<TutorResponse>tutors = tutorService.getTutorsByRegistration(registration);
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @DeleteMapping("{tutorId}")
    public ResponseEntity removeTutor(@PathVariable Long tutorId, @CurrentUser StudentPrincipal currentStudent) {
        tutorService.removeTutor(tutorId, currentStudent.getTutors());
        return new ResponseEntity(HttpStatus.OK);
    }
}
