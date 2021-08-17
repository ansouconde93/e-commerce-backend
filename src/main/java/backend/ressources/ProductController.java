package backend.ressources;

import backend.Entities.Category;
import backend.Entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductController {
    public List<Product> getAllProduct();
    public Product getProductById(Long id);
    public Product saveProduct(Product product);
    public void deleteProduct(Long id);
    public List<Product> getSelectedProducte();
    public List<Product> getPromotionProducts();
    public List<Product> getAvaibleProducte();
    public List<Category> getProductsByKeyWord(String keyWord);
    public byte[] getPhoto(long id);
    public void updatePhotoProduct(MultipartFile file,long id) throws IOException;
}
