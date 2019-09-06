package com.example.springretry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.ulta.product.response.ProductResponse;

public interface BackendAdapter {

	@Retryable(value = { RemoteServiceNotAvailableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ProductResponse getBackendResponse(boolean simulateretry);

	@Recover
	public ProductResponse getBackendResponseFallback(RuntimeException e);

}
