package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
@Autowired
    ProductRepository productRepository;

@Test
    public void initTest(){
}

@Test
    public void getProducts() throws Exception{
    Pageable pageable = PageRequest.of(0, 20);
    Page<Product> products = productRepository.getProducts(pageable);
    for(Product product: products){
        System.out.println(product.getProductName());
        System.out.println(product.getCategory().getName());
    }
}
}
