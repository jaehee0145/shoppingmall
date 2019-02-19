package my.examples.JBCmart.repository.custom;

import my.examples.JBCmart.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    public List<Product> getProducts(Long categoryId, int start, int limit, String searchKind, String searchStr);
    public Long getProductsCount(Long categoryId, String serrchKind, String searchStr);
}
