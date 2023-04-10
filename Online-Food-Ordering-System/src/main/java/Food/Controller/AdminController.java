package Food.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Food.Model.Category;
import Food.Service.CategoryService;



@Controller
public class AdminController {

	@Autowired
	private CategoryService cService;

	@GetMapping("/admin")
	public String adminHomePage() {
		return "adminHome";
	}

	// <--***** Category *****-->

	@GetMapping("/admin/categories")
	public String adminCategories(Model m) {
		List<Category> list = cService.getCategoryList();
		m.addAttribute("categories", list);
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String adminAddCategories(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	// create category
	@PostMapping("/admin/categories/add")
	public String adminPostCategories(@ModelAttribute("category") Category category) {
		cService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	//delete category
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategories(@PathVariable Integer id) {
		cService.deleteCategory(id);
		return "redirect:/admin/categories";
	}
	
	//update category
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategories(@PathVariable Integer id, Model model) {
		
		Category findCat = cService.finCategoryById(id);
		
		model.addAttribute("category",findCat);
		
		return "categoriesAdd";
	}
}
