package backend.DAO;

import backend.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findBySelectedIsTrue();
    public List<Product> findByPromotionIsTrue();
    public List<Product> findByAvaibleIsTrue();
    public List<Product> findProductByNameContains(String keyword);

}
