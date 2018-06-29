package ruan.eloy.backend.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruan.eloy.backend.dto.TutorResponse;
import ruan.eloy.backend.exception.StudentNotFoundException;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.entity.Tutor;
import ruan.eloy.backend.dto.TutorRequest;
import ruan.eloy.backend.exception.TutorNotFoundException;
import ruan.eloy.backend.repository.StudentRepository;
import ruan.eloy.backend.repository.TutorRepository;

import javax.validation.Valid;
import java.util.*;

@Service
public class TutorService {

    private TutorRepository tutorRepository;

    private StudentRepository studentRepository;

    private ModelMapper modelMapper;

    private TypeMap<Tutor, TutorResponse> typeMap;

    @Autowired
    public TutorService(TutorRepository tutorRepository, StudentRepository studentRepository,
                        ModelMapper modelMapper) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        setTutorMapping();
    }

    private void setTutorMapping() {
        this.typeMap = this.modelMapper.createTypeMap(Tutor.class, TutorResponse.class);
        this.typeMap.addMapping(tutor -> tutor.getStudent().getName(), TutorResponse::setName);
        this.typeMap.addMapping(tutor -> tutor.getStudent().getEmail(), TutorResponse::setEmail);
        this.typeMap.addMapping(tutor -> tutor.getStudent().getRegistration(), TutorResponse::setRegistration);
    }

    @Transactional
    public TutorResponse create(@Valid TutorRequest tutorRequest, String registration) {
        Student student = getStudent(registration);
        Tutor tutor = new Tutor(tutorRequest.getSubject(), tutorRequest.getProficiency());
        tutor.setStudent(student);
        tutorRepository.save(tutor);

        student.addTutor(tutor);
        studentRepository.save(student);
        return this.typeMap.map(tutor);
    }

    public List<TutorResponse> getTutorsByRegistration(String registration) {
        Student student = getStudent(registration);
        Set<Tutor> tutors = student.getTutors();
        return convertTutors(tutors);
    }

    public List<TutorResponse> getAll() {
        Collection<Tutor> tutors = tutorRepository.findAll();
        return convertTutors(tutors);
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

    private boolean ownsTutor(Long id, Collection<Tutor> tutors) {
        for (Tutor tutor: tutors) {
            if(tutor.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private List<TutorResponse> convertTutors(Collection<Tutor> tutors) {
        List<TutorResponse> tutorResponses = new ArrayList<>();
        for (Tutor tutor : tutors) {
            TutorResponse tutorResponse = this.typeMap.map(tutor);
            tutorResponses.add(tutorResponse);
        }
        return tutorResponses;
    }
}
