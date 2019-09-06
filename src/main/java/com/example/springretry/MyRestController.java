package com.example.springretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ulta.product.response.ProductResponse;

@RestController
public class MyRestController {

	@Autowired
	BackendAdapter backendAdapter;

	@GetMapping("/retry")
	@ExceptionHandler({ Exception.class })
	public ProductResponse validateSPringRetryCapability(@RequestParam(required = false) boolean simulateretry) {
		System.out.println("===============================");
		System.out.println("Inside RestController mathod..");
		return backendAdapter.getBackendResponse(simulateretry);
	}
}
