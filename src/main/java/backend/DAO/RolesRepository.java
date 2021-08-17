package backend.DAO;

import backend.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    public Roles findByNomrole(String nomRole);
}
