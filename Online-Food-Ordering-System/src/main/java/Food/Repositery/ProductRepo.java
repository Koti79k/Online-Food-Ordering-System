package Food.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Food.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
