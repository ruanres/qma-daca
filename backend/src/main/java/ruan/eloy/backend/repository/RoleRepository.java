package ruan.eloy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruan.eloy.backend.entity.Role;
import ruan.eloy.backend.entity.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
