package Food.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import Food.Model.Category;
import Food.Model.Product;
import Food.Model.ProductDTO;
import Food.Service.CategoryService;
import Food.Service.ProductService;



@Controller
public class AdminController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productsimg";
	
	@Autowired
	private CategoryService cService;
	
	@Autowired
	private ProductService pService;

	@GetMapping("/admin")
	public String adminHomePage() {
		return "adminHome";
	}

// ================== Category ===================== -->

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
	
//	================ Product Controller ================= -->
	
	@GetMapping("/admin/products")
	public String adminProducts(Model m) {
		List<Product> productList = pService.getProductList();
		m.addAttribute("products",productList);
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String adminAddProducts(Model m) {
		
		m.addAttribute("productDTO",new ProductDTO());
		m.addAttribute("categories",cService.getCategoryList());
		
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String addProducts(@ModelAttribute("productDTO") ProductDTO productDTO,
			                  @RequestParam("productImage")MultipartFile file,
			                  @RequestParam("imgName") String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(cService.finCategoryById(productDTO.getCategoryId()));
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setWeight(productDTO.getWeight());
		
		//for image we divide 2 for static folder productimg and for database
		
		String imageUUID;
		
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory,imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID = imgName;
		}
		
		product.setImageName(imageUUID);
		
		pService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	  //delete Products
		@GetMapping("/admin/product/delete/{id}")
		public String deleteProducts(@PathVariable Integer id) {
			pService.removeProductById(id);
			return "redirect:/admin/products";
		}
		
		//update category
		@GetMapping("/admin/product/update/{id}")
		public String updateProducts(@PathVariable Integer id, Model m) {
			
			Product products =  pService.getProductById(id);
			
			ProductDTO proDTO = new ProductDTO();
			proDTO.setId(products.getId());
			proDTO.setName(products.getName());
			proDTO.setCategoryId(products.getCategory().getId());
			proDTO.setDescription(products.getDescription());
			proDTO.setImageName(products.getImageName());
			proDTO.setWeight(products.getWeight());
			proDTO.setPrice(products.getPrice());
			
			m.addAttribute("categories", cService.getCategoryList());
			m.addAttribute("productDTO",proDTO);
			
			return "productsAdd";
		}
	
}
