package ag.selm.catalogue.repository;

import ag.selm.catalogue.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Product findById(int productId);

    void deleteById(Integer id);
}
