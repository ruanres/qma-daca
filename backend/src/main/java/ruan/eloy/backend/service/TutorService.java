package ruan.eloy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruan.eloy.backend.exception.StudentNotFoundException;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.dto.TutorRequest;
import ruan.eloy.backend.exception.TutorNotFoundException;
import ruan.eloy.backend.repository.StudentRepository;
import ruan.eloy.backend.repository.TutorRepository;

import javax.validation.Valid;
import java.util.Iterator;
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
    public Tutor create(@Valid TutorRequest tutorRequest, String registration) {
        Student student = getStudent(registration);
        Tutor tutor = new Tutor(tutorRequest.getSubject(), tutorRequest.getProficiency());
        tutor.setStudent(student);
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

    public void removeTutor(Long id, Set<Tutor> tutors) {
        if(ownsTutor(id, tutors)) {
            tutorRepository.deleteById(id);
        } else {
            throw new TutorNotFoundException();
        }
    }

    private Student getStudent(String registration) {
        return studentRepository.findByRegistration(registration)
                .orElseThrow(() -> new StudentNotFoundException());
    }

    private boolean ownsTutor(Long id, Set<Tutor> tutors) {
        Iterator iterator = tutors.iterator();
        while (iterator.hasNext()) {
            Tutor tutor = (Tutor) iterator.next();
            if(tutor.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
