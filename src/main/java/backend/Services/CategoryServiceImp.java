package backend.Services;

import backend.DAO.CategoryRepository;
import backend.DAO.ProductRepository;
import backend.Entities.Category;
import backend.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(cat -> {
            Category c = new Category();
            c.setName(cat.getName());
            c.setId(cat.getId());
            c.setDescription(cat.getDescription());
            List<Product> products = new ArrayList<>();
            cat.getProducts().forEach(p ->{
                Product product = new Product();
                product.setIdproduct(p.getIdproduct());
                product.setPrice(p.getPrice());
                product.setPromotion(p.isPromotion());
                product.setAvaible(p.isAvaible());
                product.setPhotoname(p.getPhotoname());
                product.setSelected(p.isSelected());
                product.setDescription(p.getDescription());
                product.setName(p.getName());
                products.add(product);
            });
            c.setProducts(products);
            categories.add(c);
        });
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category cat = category.get();
            Category c = new Category();
            c.setName(cat.getName());
            c.setId(cat.getId());
            c.setDescription(cat.getDescription());
            List<Product> products = new ArrayList<>();
            cat.getProducts().forEach(p ->{
                Product product = new Product();
                product.setIdproduct(p.getIdproduct());
                product.setPrice(p.getPrice());
                product.setPromotion(p.isPromotion());
                product.setAvaible(p.isAvaible());
                product.setPhotoname(p.getPhotoname());
                product.setSelected(p.isSelected());
                product.setDescription(p.getDescription());
                product.setName(p.getName());
                products.add(product);
            });
            c.setProducts(products);
            return c;
        }
        return null;
    }

    @Override
    public Category saveCategory(Category category) {
        Category category1 = categoryRepository.findByName(category.getName());
        Category cat;
        if (category1 == null)
            cat = categoryRepository.save(category);
        else
            cat = category1;
        if (cat.getProducts() != null){
            cat.getProducts().forEach(r->{
                productRepository.findById(r.getIdproduct()).get().setCategory(cat);
            });
        }
        Category c = new Category();
        c.setName(cat.getName());
        c.setId(cat.getId());
        c.setDescription(cat.getDescription());
        List<Product> products = new ArrayList<>();
        cat.getProducts().forEach(p ->{
            Product product = new Product();
            product.setIdproduct(p.getIdproduct());
            product.setPrice(p.getPrice());
            product.setPromotion(p.isPromotion());
            product.setAvaible(p.isAvaible());
            product.setPhotoname(p.getPhotoname());
            product.setSelected(p.isSelected());
            product.setDescription(p.getDescription());
            product.setName(p.getName());
            products.add(product);
        });
        c.setProducts(products);
        return c;
    }

    @Override
    public Category deleteCategory(Long id) {
        Optional<Category> cat = categoryRepository.findById(id);
        if (cat.isPresent()) {
            categoryRepository.deleteById(id);
            return cat.get();
        }
        return null;
    }
}
