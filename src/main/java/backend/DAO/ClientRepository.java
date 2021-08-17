package backend.DAO;

import backend.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findByUsername(String username);
}
