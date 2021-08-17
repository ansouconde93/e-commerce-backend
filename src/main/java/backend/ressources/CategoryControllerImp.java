package backend.ressources;

import backend.Entities.Category;
import backend.Services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@Api("Category access controller")
public class CategoryControllerImp implements CategoryController{
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Operation to fetch all categories from data base." +
            " It does not need any argument, you just call it by its path")
    @Override
    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @ApiOperation(value = "Operation to fetch category by its id from data base." +
            " It need id argument you can pass in the url ")
    @Override
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @ApiOperation(value = "Operation to save category in data base." +
            " It need saving category in argument")
    @Override
    @PostMapping("/category/save")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @ApiOperation(value = "Operation to delete category by id in data base." +
            " It need id argument you can pass in the url ")
    @Override
    @DeleteMapping("/category/delete/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

}
