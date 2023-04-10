package Food.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Food.Model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
