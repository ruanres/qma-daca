package ruan.eloy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruan.eloy.backend.Exceptions.StudentNotFoundException;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.payload.TutorRequest;
import ruan.eloy.backend.repository.StudentRepository;
import ruan.eloy.backend.repository.TutorRepository;

import javax.validation.Valid;

@Service
public class TutorService {

    private TutorRepository tutorRepository;

    private StudentRepository studentRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository, StudentRepository studentRepository) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Tutor create(@Valid TutorRequest tutorRequest) {
        Student student = studentRepository.findByRegistration(tutorRequest.getRegistration())
                .orElseThrow(() -> new StudentNotFoundException());

        Tutor tutor = new Tutor(tutorRequest.getSubject(), tutorRequest.getProficiency(), student);
        tutorRepository.save(tutor);

        student.addTutor(tutor);
        studentRepository.save(student);

        return tutor;
    }

    public Iterable<Tutor> getAll() {
        return tutorRepository.findAll();
    }
}
