package backend.ressources;

import backend.Entities.Category;
import backend.Entities.Product;
import backend.Services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin("*")
@Api("Products access controller")
public class ProductControllerImp implements ProductController{
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Operation to fetch all products from data base." +
            " It does not need any argument, you just call it by its path")
    @Override
    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @ApiOperation(value = "Operation to fetch product by its id from data base." +
            " It need id argument you can pass in the url ")
    @Override
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @ApiOperation(value = "Operation to save product in data base." +
            " It need saving product in argument")
    @Override
    @PostMapping("/product/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @ApiOperation(value = "Operation to delete product by id in data base." +
            " It need id argument you can pass in the url ")
    @Override
    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
        } catch (IOException e) {
        }
    }

    @ApiOperation(value = "Operation to fetch all selected products from data base." +
            " It does not need any argument, you just call it by its path")
    @Override
    @GetMapping("/product/selected")
    public List<Product> getSelectedProducte() {
        return productService.getSelectedProducte();
    }

    @ApiOperation(value = "Operation to fetch all promotions products from data base." +
            " It does not need any argument, you just call it by its path")
    @Override
    @GetMapping("/product/promotion")
    public List<Product> getPromotionProducts() {
        return productService.getPromotionProducts();
    }

    @ApiOperation(value = "Operation to fetch all available products from data base." +
            " It does not need any argument, you just call it by its path")
    @Override
    @GetMapping("/product/avalable")
    public List<Product> getAvaibleProducte() {
        return productService.getAvaibleProducte();
    }

    @ApiOperation(value = "Operation to fetch all products from data base witch contains given key word." +
            " It need keyword in argument.")
    @Override
    @PostMapping("/product/keyword")
    public List<Category> getProductsByKeyWord(@RequestBody String keyWord) {
        return productService.getProductsByKeyWord(keyWord);
    }

    @ApiOperation(value = "Operation to get product photo from user home directory." +
            " It need product id in argument, you can pass it in the url")
    @Override
    @GetMapping(path = "/product/photo/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable long id) {
        try{
            return productService.getPhoto(id);
        }catch (Exception e){
            return null;
        }
    }

    @ApiOperation(value = "Operation to update product photo." +
            " It need two arguments : photo and product id.")
    @Override
    @PostMapping("/uploadPhotoProduct/{id}")
    public void updatePhotoProduct(@RequestParam("file") MultipartFile file, @PathVariable long id) throws IOException {
         productService.updatePhotoProduct(file,id);
    }
}
