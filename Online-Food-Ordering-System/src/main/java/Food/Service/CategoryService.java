package Food.Service;

import java.util.List;

import Food.Model.Category;



public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public List<Category> getCategoryList();
	
	public Category deleteCategory(Integer id);
	
	public Category finCategoryById(Integer id);
}
