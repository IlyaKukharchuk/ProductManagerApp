package ag.selm.catalogue.repository;

import ag.selm.catalogue.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository{
     private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());

     @Override
     public List<Product> findAll() {
          return Collections.unmodifiableList(productList);
     }

     @Override
     public Product save(Product product) {
          product.setId(this.productList.stream()
                  .max(Comparator.comparingInt(Product :: getId))
                  .map(Product :: getId)
                  .orElse(0)+1);
          this.productList.add(product);
          return product;
     }

     @Override
     public Product findById(int productId) {
          return productList.stream()
                  .filter(product -> product.getId() == productId)
                  .findFirst()
                  .orElse(null);
     }

     @Override
     public void deleteById(Integer id) {
          productList.remove(id-1);
     }
}
