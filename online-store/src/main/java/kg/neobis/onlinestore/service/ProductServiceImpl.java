package kg.neobis.onlinestore.service;

import kg.neobis.onlinestore.entity.Category;
import kg.neobis.onlinestore.entity.Product;
import kg.neobis.onlinestore.model.ProductModel;
import kg.neobis.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product create(ProductModel productModel) {
        Category category = categoryService.getById(productModel.getCategoryId());
        Product product = new Product();
        if(category != null){
            product.setName(productModel.getName());
            product.setPrice(productModel.getPrice());
            product.setCategory(category);
            product.setDateCreated(LocalDateTime.now());
            productRepository.save(product);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product deleteById(Long id) {
       Product product = getById(id);
       if(product != null ){
           productRepository.deleteById(id);
       }
       return product;
    }
}
