package ch.bytecrowd.writesonicclient.conf;

import io.netty.handler.codec.http.HttpHeaderValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private final String apiKey;
    private final String baseUrl;

    public WebClientConfiguration(
            @Value("${writesonic.api.key}") String apiKey,
            @Value("${writesonic.api.baseUrl}") String baseUrl
    ) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Bean
    public WebClient createWebClientForWriteSonic() {
        final String apiKeyHeader = "X-API-KEY";
        return WebClient.builder()
                .baseUrl(baseUrl + "/v2/business/content/chatsonic?engine=premium")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON.toString())
                .defaultHeader(HttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON.toString())
                .defaultHeader(apiKeyHeader, apiKey)
                .build();
    }
}
