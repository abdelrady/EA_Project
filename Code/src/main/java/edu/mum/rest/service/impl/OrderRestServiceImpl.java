package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Order;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.OrderRestService;

@Service
public class OrderRestServiceImpl implements OrderRestService {
	
	@Autowired
	RestHttpHeader restHttpHeader;
	
	@Override
	public List<Order> findAll(){
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/SpringArrival_RestService/orders", HttpMethod.GET, restHttpHeader.getHttpEntity(), Order[].class).getBody());
	
	}
}
