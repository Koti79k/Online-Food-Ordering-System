package Food.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    
    private Double price;
    private Double weight;
    private String description;
    private String imageName;
    
}
