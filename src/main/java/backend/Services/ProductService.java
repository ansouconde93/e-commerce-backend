package backend.Services;

import backend.Entities.Category;
import backend.Entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();
    public Product getProductById(Long id);
    public Product saveProduct(Product product);
    public void deleteProduct(Long id) throws IOException;
    public List<Product> getSelectedProducte();
    public List<Product> getPromotionProducts();
    public List<Product> getAvaibleProducte();
    public List<Category> getProductsByKeyWord(String keyWord);
    public byte[] getPhoto(long id) throws IOException;
    public void updatePhotoProduct(MultipartFile file, long id) throws IOException;
}
