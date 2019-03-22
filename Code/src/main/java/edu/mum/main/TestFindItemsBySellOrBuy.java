package edu.mum.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.domain.Product;
import edu.mum.domain.User;
import edu.mum.service.ProductService;
import edu.mum.service.UserService;

@Component
public class TestFindItemsBySellOrBuy{

	@Autowired
	ProductService itemService;
	
 	@Autowired
	UserService userService;	

	public void test() {
 

	 	System.out.println();
	    System.out.println("********** find By Buyer Or Seller  ***************");
 
	    User buyer = userService.findOne(2L); // "Jane Doe"
	    User seller = userService.findOne(1L);  // John Doe
	    
	    // Test JPQL query
//	    List<Item> items = itemService.findBySellerOrBuyer(null, buyer,null);	     // Shoes  
//	    List<Item> items = itemService.findBySellerOrBuyer(22, null,seller);	 //Sled    
//	    List<Item> items = itemService.findBySellerOrBuyer(22, buyer,seller);	 // Sled & Shoes

	    // Test Criteria query
	    List<Product> items = itemService.findItemCriteria(null,buyer,null);    // Shoes  
//	    List<Item> items = itemService.findItemCriteria(22,null,seller);   //Sled 
//	    List<Item> items = itemService.findItemCriteria(22,buyer,seller);  // Sled & Shoes


	 	   System.out.println();
	    for (Product itemFound : items) {
		    System.out.println("Item Name : " + itemFound.getName());
	    }
	    
	}
}
