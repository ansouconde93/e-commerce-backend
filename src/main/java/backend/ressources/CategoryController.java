package backend.ressources;

import backend.Entities.Category;

import java.util.List;

public interface CategoryController {
    public List<Category> getAllCategory();
    public Category getCategoryById(Long id);
    public Category saveCategory(Category category);
    public Category deleteCategory(Long id);
}
