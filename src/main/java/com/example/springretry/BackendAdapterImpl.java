package com.example.springretry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ulta.product.exception.ErrorDetails;
import com.ulta.product.response.ProductBean;
import com.ulta.product.response.ProductResponse;

@Service
public class BackendAdapterImpl implements BackendAdapter {

	@Override
	public ProductResponse getBackendResponse(boolean simulateretry) {

		ProductResponse result= new ProductResponse();
		try {
			System.out.println("20 second");
		if (simulateretry) {
			System.out.println("try to get products from ProductService.");
			final String uri = "http://localhost:9090/v1/products";
		     
		    RestTemplate restTemplate = new RestTemplate();
		     result = restTemplate.getForObject(uri, ProductResponse.class);
		     
		}
		}
		catch(Exception e) {
			throw new RemoteServiceNotAvailableException("Product Service not available");
		}
			
			
		
		return result;
	}

	@Override
	public ProductResponse getBackendResponseFallback(RuntimeException e) {
		System.out.println("All retries completed, so Fallback method called!!!");
		ProductResponse productResponse = new ProductResponse();
		List<ProductBean> product = new ArrayList<ProductBean>();
		ProductBean obj = new ProductBean();
		product.add(obj );
		//productResponse.setProduct(product );
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Sorry! Product service is not available. You can check just Sample Response right now", String.valueOf(HttpStatus.NO_CONTENT));
		productResponse.setErrorDetails(errorDetails );
		return productResponse;
	}
}
