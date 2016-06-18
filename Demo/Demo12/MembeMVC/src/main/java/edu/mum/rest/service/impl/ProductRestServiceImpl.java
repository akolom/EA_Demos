package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.domain.Product;
import edu.mum.rest.RemoteApi;
import edu.mum.rest.service.ProductRestService;

@Component
public class ProductRestServiceImpl  implements ProductRestService {

	@Autowired
	RemoteApi remoteApi;
	
	public List<Product> findAll() {
		
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/MemberRest/products/", HttpMethod.GET, remoteApi.getHttpEntity(), Product[].class).getBody());
//		return Arrays.asList(restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/", HttpMethod.GET, remoteApi.getHttpEntity(), Product[].class).getBody());
	}

	public Product findOne(Long index) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
//		return (restTemplate.exchange("http://localhost:8080/JerseyRestSecurity/rest/products/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), Product.class).getBody());
		return (restTemplate.exchange("http://localhost:8080/MemberRest/products/"+ index, HttpMethod.GET, remoteApi.getHttpEntity(), Product.class).getBody());
	}

	public Product save(Product product) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, remoteApi.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/MemberRest/products/add/", httpEntity, Product.class);
// 		restTemplate.postForObject("http://localhost:8080/JerseyRestSecurity/rest/products/", httpEntity, Product.class);
		return null;
	}

}
