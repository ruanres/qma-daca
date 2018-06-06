package ruan.eloy.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ruan.eloy.backend.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRegistration(String registration);

    Optional<Student> findById(Long id);

    Optional<Student> findByRegistrationOrEmail(String registration, String email);

    Boolean existsByRegistration(String registration);

    Boolean existsByEmail(String email);
}
