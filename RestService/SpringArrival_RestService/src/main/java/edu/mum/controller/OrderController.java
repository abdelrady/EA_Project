package edu.mum.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.domain.Order;
import edu.mum.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
 
 	@RequestMapping({"","/all"})
	public List<Order> list(Model model) {
		return orderService.findAll();
	}
	
 	@RequestMapping("/{id}")
	public Order getItemById(Model model, @PathVariable("id") Long id) {
 		return orderService.findOne(id);
	}

/*	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void processAddNewItemForm(@RequestBody Order itemToBeAdded) {
			orderService.save(itemToBeAdded);
	}*/
}
