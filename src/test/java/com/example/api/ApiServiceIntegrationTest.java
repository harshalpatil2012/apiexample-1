package com.example.api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.api.beans.ApplicationInstanceResponse;
import com.example.api.beans.ApplicationMapResponse;
import com.example.api.beans.JourneyResponse;
import com.example.api.impl.ApiService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiServiceIntegrationTest {

    @Autowired
    private ApiService apiService;

    @MockBean
    private WebClient.Builder webClientBuilder;

    @Test
    public void testFetchAndCacheApplicationMapIntegration() {
        // Mock WebClient behavior for Application Map
        WebClient webClient = WebClient.builder().baseUrl("https://your-api-url.com").build();
        when(webClientBuilder.baseUrl("https://your-api-url.com")).thenReturn(webClient);

        // Simulate a response from the actual API
        ApplicationMapResponse response = new ApplicationMapResponse();
        // Set up the response as needed

        when(webClient.get().uri("/path/to/application-map-get/endpoint").retrieve().bodyToMono(ApplicationMapResponse.class))
            .thenReturn(Mono.just(response));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheApplicationMap())
            .expectNext(response)
            .verifyComplete();
    }

    @Test
    public void testFetchAndCacheJourneyResponseIntegration() {
        // Mock WebClient behavior for Journey Response
        WebClient webClient = WebClient.builder().baseUrl("https://your-api-url.com").build();
        when(webClientBuilder.baseUrl("https://your-api-url.com")).thenReturn(webClient);

        // Simulate a response from the actual API
        JourneyResponse response = new JourneyResponse();
        // Set up the response as needed

        when(webClient.get().uri("/path/to/journey-response-get/endpoint").retrieve().bodyToMono(JourneyResponse.class))
            .thenReturn(Mono.just(response));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheJourneyResponse())
            .expectNext(response)
            .verifyComplete();
    }

    @Test
    public void testFetchAndCacheApplicationInstanceIntegration() {
        // Mock WebClient behavior for Application Instance
        WebClient webClient = WebClient.builder().baseUrl("https://your-api-url.com").build();
        when(webClientBuilder.baseUrl("https://your-api-url.com")).thenReturn(webClient);

        // Simulate a response from the actual API
        ApplicationInstanceResponse response = new ApplicationInstanceResponse();
        // Set up the response as needed

        when(webClient.get().uri("/path/to/application-instance-get/endpoint").retrieve().bodyToMono(ApplicationInstanceResponse.class))
            .thenReturn(Mono.just(response));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheApplicationInstance())
            .expectNext(response)
            .verifyComplete();
    }

    @Test
    public void testFetchAndCacheBundleConfigurationIntegration() {
        // Mock WebClient behavior for Bundle Configuration
        WebClient webClient = WebClient.builder().baseUrl("https://your-api-url.com").build();
        when(webClientBuilder.baseUrl("https://your-api-url.com")).thenReturn(webClient);

        // Simulate a response from the actual API
        BundleConfigResponse response = new BundleConfigResponse();
        // Set up the response as needed

        when(webClient.get().uri("/path/to/bundle-config-get/endpoint").retrieve().bodyToMono(BundleConfigResponse.class))
            .thenReturn(Mono.just(response));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheBundleConfiguration())
            .expectNext(response)
            .verifyComplete();
    }
}
