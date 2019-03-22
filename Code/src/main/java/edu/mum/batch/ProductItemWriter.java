package edu.mum.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;

/*
 * To write data to database
 */
public class ProductItemWriter implements ItemWriter<Product>   {

 	private ProductService itemService;

	@Override
	public void write(List<? extends Product> products) throws Exception {
		for (Product product : products) {
			itemService.save(product);
		}
	}

	// Injected in batch-config.xml
	public void setProductService(ProductService productService) {
		this.itemService = productService;
	}

}

