package my.examples.JBCmart.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.domain.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> getProducts(Long categoryId, int start, int limit, String searchKind, String searchStr) {

        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).innerJoin(qProduct.category).fetchJoin().distinct();

        if(categoryId !=null){
            jpqlQuery.where(qProduct.category.id.eq(categoryId));
        }
        if("NAME_SEARCH".equals(searchKind)){
            jpqlQuery.where(qProduct.productName.like("%"+searchStr+"%"));
        }
        jpqlQuery.orderBy(qProduct.productId.desc());
        jpqlQuery.offset(start).limit(limit);

        return jpqlQuery.fetch();
    }

    @Override
    public Long getProductsCount(Long categoryId, String searchKind, String searchStr) {

        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).innerJoin(qProduct.category).fetchJoin().distinct();
        if(categoryId !=null){
            jpqlQuery.where(qProduct.category.id.eq(categoryId));
        }

        if("NAME_SEARCH".equals(searchKind)){
            jpqlQuery.where(qProduct.category.name.like("%"+searchStr+"%"));
        }
        return jpqlQuery.fetchCount();
    }
}
