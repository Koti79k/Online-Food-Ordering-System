package Food.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import Food.Model.Product;
import Food.Service.CategoryService;
import Food.Service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping({"/" , "/home"})
	public String home(Model model) {		
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {

		model.addAttribute("categories",categoryService.getCategoryList());
		model.addAttribute("products",productService.getProductList());
		
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
    public String shop(@PathVariable Integer id, Model model) {
		
		List<Product> productList =  productService.getProductsByCategoryId(id);
		
		model.addAttribute("products",productList);
		model.addAttribute("categories",categoryService.getCategoryList());
		
		return "shop";
	}
	
   @GetMapping("/shop/viewproduct/{id}")
   public String viewProduct(@PathVariable Integer id, Model model) {
		
		Product product = productService.getProductById(id);
		
		model.addAttribute("product",product);
		model.addAttribute("categories",categoryService.getCategoryList());
		
		return "viewProduct";
	}
}
