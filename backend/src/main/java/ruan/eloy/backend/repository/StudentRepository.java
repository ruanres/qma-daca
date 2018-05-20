package ruan.eloy.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ruan.eloy.backend.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByEmail(String email);
}
