package ruan.eloy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruan.eloy.backend.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
