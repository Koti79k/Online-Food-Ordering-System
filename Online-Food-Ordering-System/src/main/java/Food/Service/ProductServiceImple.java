package Food.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Food.Model.Product;
import Food.Repositery.ProductRepo;

@Service
public class ProductServiceImple implements ProductService{

	@Autowired
	private ProductRepo pRepositery;
	
	@Override
	public Product addProduct(Product product) {
       return pRepositery.save(product);
	}

	@Override
	public List<Product> getProductList() {
		return pRepositery.findAll();
	}

	@Override
	public Product removeProductById(Integer id) {
		Product product=null;
		Optional<Product> find_Product = pRepositery.findById(id);
		if(find_Product.isPresent()) {
			pRepositery.deleteById(id);
			product=find_Product.get();
		}
		else {
			product=null;
		}
		return product;
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<Product> find_Product = pRepositery.findById(id);
		return find_Product.get();
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer id) {
		
		List<Product> productsList=pRepositery.findAll();
		
		List<Product> list = new ArrayList<>();
		
		if(productsList.size()>0) {
			for(Product i:productsList) {
				if(i.getCategory().getId()==id) {
					list.add(i);
				}
			}
		}
		return list;	
	}

}
