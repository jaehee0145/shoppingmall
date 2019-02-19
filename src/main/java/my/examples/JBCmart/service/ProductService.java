package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Category;
import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.CategoryRepository;
import my.examples.JBCmart.repository.ProductRepository;
import my.examples.JBCmart.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public final static String NAME_SEARCH = "name_search";


    @Transactional
    public Product addProduct(Product product, Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        product.setCategory(optionalCategory.get());
        return productRepository.save(product);
    }


    @Transactional
    public Product addProduct(Product product, Long categoryId, Long userId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Optional<User> optionalUser = userRepository.findById(userId);

        product.setUser(optionalUser.get());
        product.setCategory(optionalCategory.get());
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> getProducts(int page, Long categoryId,  String searchKind, String searchStr) {
    int limit = 15;
    int start = page * limit -limit;
    return productRepository.getProducts(categoryId, start, limit, searchKind, searchStr);
    }

    @Transactional(readOnly = true)
    public Product getProducts(Long productId) {
        return productRepository.getProducts(productId);
    }
}
