package ruan.eloy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.payload.TutorRequest;
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
    public ResponseEntity<Tutor> createOne(@RequestBody TutorRequest tutorRequest) {
        Tutor tutor = tutorService.create(tutorRequest);
        return new ResponseEntity<>(tutor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> findAll() {
        List<Tutor> tutors = (List<Tutor>) tutorService.getAll();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }
}
