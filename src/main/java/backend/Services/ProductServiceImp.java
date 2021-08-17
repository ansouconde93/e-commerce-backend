package backend.Services;

import backend.DAO.ProductRepository;
import backend.Entities.Category;
import backend.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional

public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    String imageDirectory = "images/products";

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll(Sort.by("name")).forEach( pr ->{
            Product p = new Product();
            p.setIdproduct(pr.getIdproduct());
            p.setName(pr.getName());
            p.setSelected(pr.isSelected());
            p.setPromotion(pr.isPromotion());
            p.setPrice(pr.getPrice());
            p.setPhotoname(pr.getPhotoname());
            p.setAvaible(pr.isAvaible());
            p.setDescription(pr.getDescription());
            Category category = new Category();
            category.setId(pr.getCategory().getId());
            category.setDescription(pr.getCategory().getDescription());
            category.setName(pr.getCategory().getName());
            category.setPhoto(pr.getCategory().getPhoto());
            p.setCategory(category);
            products.add(p);
        });
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> pr = productRepository.findById(id);
        if (pr.isPresent()) {
            Product p = new Product();
            p.setIdproduct(pr.get().getIdproduct());
            p.setName(pr.get().getName());
            p.setSelected(pr.get().isSelected());
            p.setPromotion(pr.get().isPromotion());
            p.setPrice(pr.get().getPrice());
            p.setPhotoname(pr.get().getPhotoname());
            p.setAvaible(pr.get().isAvaible());
            p.setDescription(pr.get().getDescription());
            Category category = new Category();
            category.setId(pr.get().getCategory().getId());
            category.setDescription(pr.get().getCategory().getDescription());
            category.setName(pr.get().getCategory().getName());
            category.setPhoto(pr.get().getCategory().getPhoto());
            p.setCategory(category);
            return p;
        }
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        Product pr = productRepository.save(product);
        Product p = new Product();
        p.setIdproduct(pr.getIdproduct());
        p.setName(pr.getName());
        p.setSelected(pr.isSelected());
        p.setPromotion(pr.isPromotion());
        p.setPrice(pr.getPrice());
        p.setPhotoname(pr.getPhotoname());
        p.setAvaible(pr.isAvaible());
        p.setDescription(pr.getDescription());
        Category category = new Category();
        category.setId(pr.getCategory().getId());
        category.setDescription(pr.getCategory().getDescription());
        category.setName(pr.getCategory().getName());
        category.setPhoto(pr.getCategory().getPhoto());
        p.setCategory(category);
        return p;
    }

    @Override
     public void deleteProduct(Long id) throws IOException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            /*
            Files.delete(Paths.get(
                    System.getProperty("user.home")+"/project/images/product/"+product.get().getPhotoname()));
            */

            Files.delete(Paths.get(imageDirectory+"/"+product.get().getPhotoname()));
        }
    }

    @Override
    public List<Product> getSelectedProducte() {
        List<Product> products = new ArrayList<>();
        productRepository.findBySelectedIsTrue().forEach( pr ->{
            Product p = new Product();
            p.setIdproduct(pr.getIdproduct());
            p.setName(pr.getName());
            p.setSelected(pr.isSelected());
            p.setPromotion(pr.isPromotion());
            p.setPrice(pr.getPrice());
            p.setPhotoname(pr.getPhotoname());
            p.setAvaible(pr.isAvaible());
            p.setDescription(pr.getDescription());
            Category category = new Category();
            category.setId(pr.getCategory().getId());
            category.setDescription(pr.getCategory().getDescription());
            category.setName(pr.getCategory().getName());
            category.setPhoto(pr.getCategory().getPhoto());
            p.setCategory(category);
            products.add(p);
        });
        return products;
    }

    @Override
    public List<Product> getPromotionProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findByPromotionIsTrue().forEach( pr ->{
            Product p = new Product();
            p.setIdproduct(pr.getIdproduct());
            p.setName(pr.getName());
            p.setSelected(pr.isSelected());
            p.setPromotion(pr.isPromotion());
            p.setPrice(pr.getPrice());
            p.setPhotoname(pr.getPhotoname());
            p.setAvaible(pr.isAvaible());
            p.setDescription(pr.getDescription());
            Category category = new Category();
            category.setId(pr.getCategory().getId());
            category.setDescription(pr.getCategory().getDescription());
            category.setName(pr.getCategory().getName());
            category.setPhoto(pr.getCategory().getPhoto());
            p.setCategory(category);
            products.add(p);
        });
        return products;
    }

    @Override
    public List<Product> getAvaibleProducte() {
        List<Product> products = new ArrayList<>();
        productRepository.findByAvaibleIsTrue().forEach( pr ->{
            Product p = new Product();
            p.setIdproduct(pr.getIdproduct());
            p.setName(pr.getName());
            p.setSelected(pr.isSelected());
            p.setPromotion(pr.isPromotion());
            p.setPrice(pr.getPrice());
            p.setPhotoname(pr.getPhotoname());
            p.setAvaible(pr.isAvaible());
            p.setDescription(pr.getDescription());
            Category category = new Category();
            category.setId(pr.getCategory().getId());
            category.setDescription(pr.getCategory().getDescription());
            category.setName(pr.getCategory().getName());
            category.setPhoto(pr.getCategory().getPhoto());
            p.setCategory(category);
            products.add(p);
        });
        return products;
    }

    @Override
    public List<Category> getProductsByKeyWord(String keyWord) {
        List<Category> categories = new ArrayList<>();
        productRepository.findProductByNameContains(keyWord).forEach( pr ->{
            Product p = new Product();
            p.setIdproduct(pr.getIdproduct());
            p.setName(pr.getName());
            p.setSelected(pr.isSelected());
            p.setPromotion(pr.isPromotion());
            p.setPrice(pr.getPrice());
            p.setPhotoname(pr.getPhotoname());
            p.setAvaible(pr.isAvaible());
            p.setDescription(pr.getDescription());
            Category category = new Category();
            category.setId(pr.getCategory().getId());
            category.setDescription(pr.getCategory().getDescription());
            category.setName(pr.getCategory().getName());
            category.setPhoto(pr.getCategory().getPhoto());
            //p.setCategory(category);
            int indice = categories.indexOf(category);
            if( indice >-1){
                categories.get(indice).getProducts().add(p);
            }else {
                category.getProducts().add(p);
                categories.add(category);
            }
        });
        return categories;
    }

    @Override
    public byte[] getPhoto(long id)  throws IOException{
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            /*
            return Files.readAllBytes(Paths.get(System
                    .getProperty("user.home")+"/project/images/product/"+product.get().getPhotoname()));
            */
            return Files.readAllBytes(Paths.get(imageDirectory+"/"+product.get().getPhotoname()));
        }
        return null;
    }

    @Override
    public void updatePhotoProduct(MultipartFile file,long id) throws IOException {
        Path productImagePath = Paths.get(imageDirectory);
        if (! Files.exists(productImagePath)){
            Files.createDirectories(productImagePath);
        }
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product p = product.get();
            p.setPhotoname(id+".png");
            /*
            Files.write(Paths.get(System
                    .getProperty("user.home")+"/project/images/product/"+p.getPhotoname()),file.getBytes());
             */
            Files.write(Paths.get(imageDirectory+"/"+p.getPhotoname()),file.getBytes());
            productRepository.save(p);
        }
    }
}
