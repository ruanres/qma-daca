package ruan.eloy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruan.eloy.backend.Exceptions.StudentNotFoundException;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.dto.TutorRequest;
import ruan.eloy.backend.repository.StudentRepository;
import ruan.eloy.backend.repository.TutorRepository;

import javax.validation.Valid;
import java.util.Set;

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
        Student student = getStudent(tutorRequest.getRegistration());
        Tutor tutor = new Tutor(tutorRequest.getSubject(), tutorRequest.getProficiency(), student);
        tutorRepository.save(tutor);

        student.addTutor(tutor);
        studentRepository.save(student);
        return tutor;
    }

    public Set<Tutor> getTutorsByRegistration(String registration) {
        Student student = getStudent(registration);
        return student.getTutors();
    }

    public Iterable<Tutor> getAll() {
        return tutorRepository.findAll();
    }

    private Student getStudent(String registration) {
        return studentRepository.findByRegistration(registration)
                .orElseThrow(() -> new StudentNotFoundException());
    }
}
