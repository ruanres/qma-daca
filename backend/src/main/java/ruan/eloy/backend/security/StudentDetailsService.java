package ruan.eloy.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.repository.StudentRepository;

/**
 * Loads the user details and returns a UserDetails object that
 * Spring Security uses for performing various authentication
 * and role based validations.
 */
@Service
public class StudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String registrationOrEmail) throws UsernameNotFoundException {
        Student student = studentRepository.findByRegistrationOrEmail(registrationOrEmail, registrationOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found with registration or email: " + registrationOrEmail));

        return StudentPrincipal.create(student);
    }

    public UserDetails loadUserById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found with id: " + id));

        return StudentPrincipal.create(student);
    }
}
