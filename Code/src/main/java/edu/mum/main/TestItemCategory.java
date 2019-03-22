package edu.mum.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;

@Component
public class TestItemCategory {

	@Autowired
	ProductService itemService;
	
	public void testItemCategory() {
		String categoryName = "Sports";
		
 				   List<Product> items =  itemService.findByCategoryName(categoryName);
	
				 	System.out.println();
				    System.out.println("********** NamedQuery -> Items by Category : " + categoryName + " ***************");
 				 	System.out.println();

					for (Product iteme : items) {
					System.out.println("Item Name : " + iteme.getName());
					}

	}
}
