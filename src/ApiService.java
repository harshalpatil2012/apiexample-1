import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    private final WebClient webClient;
    private final Map<String, ApiEntry> cachedApiData = new ConcurrentHashMap<>();
    private final Map<String, ApiAlias> cachedApplicationMap = new ConcurrentHashMap<>();

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://your-api-url.com").build();
    }

    @Scheduled(fixedRate = 60000) // Adjust the rate as needed (e.g., every minute)
    public void fetchAndCacheData() {
        fetchAndCacheApplicationMap();
        fetchAndCacheJourneyResponse();
    }

    private void fetchAndCacheApplicationMap() {
        try {
            LOGGER.info("Fetching and caching Application Map data...");
            Mono<ApplicationMapResponse> responseMono = webClient.get()
                    .uri("/path/to/application-map-get/endpoint")
                    .retrieve()
                    .bodyToMono(ApplicationMapResponse.class);

            responseMono.subscribe(
                    this::updateApplicationMapCache,
                    throwable -> handleError("Error fetching Application Map data", throwable)
            );
        } catch (Exception e) {
            handleError("An unexpected error occurred while fetching and caching Application Map data", e);
        }
    }

    private void updateApplicationMapCache(ApplicationMapResponse response) {
        if (response != null && response.getData() != null && response.getData().getApiEntries() != null) {
            LOGGER.info("Updating cache with Application Map data...");
            response.getData().getApiEntries().forEach((key, apiAlias) -> {
                LOGGER.info("Storing API Alias: {} in cache", key);
                cachedApplicationMap.put(key, apiAlias);
            });
        }
    }

    private void fetchAndCacheJourneyResponse() {
        try {
            LOGGER.info("Fetching and caching Journey Response data...");
            Mono<JourneyResponse> responseMono = webClient.get()
                    .uri("/path/to/journey-response/endpoint")
                    .retrieve()
                    .bodyToMono(JourneyResponse.class);

            responseMono.subscribe(
                    this::updateJourneyResponseCache,
                    throwable -> handleError("Error fetching Journey Response data", throwable)
            );
        } catch (Exception e) {
            handleError("An unexpected error occurred while fetching and caching Journey Response data", e);
        }
    }

    private void updateJourneyResponseCache(JourneyResponse response) {
        if (response != null && response.getData() != null && response.getData().getAttributes() != null) {
            LOGGER.info("Updating Journey Response cache...");
            String bundleName = response.getData().getAttributes().getBundleHost();
            // Store the journeyConfigs in a map with bundleName as the key
            cachedJourneyConfigs.put(bundleName, response.getData().getAttributes().getJourneyConfigs());
        }
    }

    private void handleError(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    // Implement similar methods for other endpoints using the POJO classes.

    public Map<String, ApiEntry> getCachedApiData() {
        return cachedApiData;
    }

    public Map<String, ApiAlias> getCachedApplicationMap() {
        return cachedApplicationMap;
    }

    public Map<String, JourneyResponse.JourneyConfig[]> getCachedJourneyConfigs() {
        return cachedJourneyConfigs;
    }
}

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>
<scope>test</scope>
</dependency>
<dependency>
<groupId>io.projectreactor</groupId>
<artifactId>reactor-test</artifactId>
<scope>test</scope>
</dependency>