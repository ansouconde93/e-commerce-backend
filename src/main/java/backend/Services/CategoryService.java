package backend.Services;

import backend.Entities.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Category getCategoryById(Long id);
    public Category saveCategory(Category category);
    public Category deleteCategory(Long id);
}
