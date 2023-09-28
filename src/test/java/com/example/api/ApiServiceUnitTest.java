package com.example.api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.api.beans.ApplicationInstanceResponse;
import com.example.api.beans.ApplicationMapResponse;
import com.example.api.beans.JourneyResponse;
import com.example.api.impl.ApiService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ApiServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    private ApiService apiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        apiService = new ApiService(webClientBuilder);
    }

    @Test
    public void testFetchAndCacheApplicationMap() {
        // Mock WebClient behavior for a successful response
        WebClient webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClient);
        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.RequestHeadersSpec<?> requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ApplicationMapResponse.class)).thenReturn(Mono.just(new ApplicationMapResponse()));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheApplicationMap())
            .expectNextCount(1)
            .verifyComplete();

        // Verify that WebClient is called
        verify(webClientBuilder).baseUrl(anyString());
        verify(requestHeadersUriSpec).uri(anyString());
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(ApplicationMapResponse.class);
    }

    @Test
    public void testFetchAndCacheJourneyResponse() {
        // Mock WebClient behavior for a successful response
        WebClient webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClient);
        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.RequestHeadersSpec<?> requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(JourneyResponse.class)).thenReturn(Mono.just(new JourneyResponse()));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheJourneyResponse())
            .expectNextCount(1)
            .verifyComplete();

        // Verify that WebClient is called
        verify(webClientBuilder).baseUrl(anyString());
        verify(requestHeadersUriSpec).uri(anyString());
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(JourneyResponse.class);
    }

    @Test
    public void testGetApplicationInstance() {
        // Mock WebClient behavior for a successful response
        WebClient webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClient);
        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.RequestHeadersSpec<?> requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ApplicationInstanceResponse.class)).thenReturn(Mono.just(new ApplicationInstanceResponse()));

        // Test the method
        StepVerifier.create(apiService.getApplicationInstance())
            .expectNextCount(1)
            .verifyComplete();

        // Verify that WebClient is called
        verify(webClientBuilder).baseUrl(anyString());
        verify(requestHeadersUriSpec).uri(anyString());
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(ApplicationInstanceResponse.class);
    }

    @Test
    public void testErrorHandling() {
        // Mock WebClient behavior to simulate an error
        WebClient webClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClient);
        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.RequestHeadersSpec<?> requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenThrow(new RuntimeException("Simulated error"));

        // Test the method
        StepVerifier.create(apiService.fetchAndCacheApplicationMap())
            .expectError(RuntimeException.class)
            .verify();
    }
}
