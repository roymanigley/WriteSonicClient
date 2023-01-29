package ch.bytecrowd.writesonicclient.client;

import ch.bytecrowd.writesonicclient.conf.WebClientConfiguration;
import ch.bytecrowd.writesonicclient.model.WriteSonicResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class WriteSonicClientTest {

    public static final String API_KEY = "MY_API_KEY";
    public static final String API_KEY_HEADER = "X-API-KEY";
    MockWebServer server;

    WriteSonicClient client;

    @BeforeEach
    void init() {
        server = new MockWebServer();
        String hostName = server.getHostName();
        int port = server.getPort();
        client = new WriteSonicClient(
                new WebClientConfiguration(
                        API_KEY,
                        "http://" + hostName + ":" + port
                ).createWebClientForWriteSonic()
        );
    }

    @AfterEach
    void cleanUp() throws IOException {
        server.shutdown();
    }

    @Test
    void testApiCall() throws InterruptedException {
        // GIVEN
        final String question = "Hello, how are you";
        server.enqueue(
                new MockResponse()
        );

        // WHEN
        client.callChatApi(question).block();

        // THEN
        RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod()).isEqualTo(HttpMethod.POST.name());
        assertThat(request.getHeader(API_KEY_HEADER)).isEqualTo(API_KEY);
        assertThat(request.getHeader(HttpHeaders.CONTENT_TYPE)).isEqualTo(HttpHeaderValues.APPLICATION_JSON.toString());
        assertThat(request.getHeader(HttpHeaders.ACCEPT)).isEqualTo(HttpHeaderValues.APPLICATION_JSON.toString());
        assertThat(request.getBody().readString(StandardCharsets.UTF_8)).isEqualTo(
                String.format("{\"enable_google_results\":true,\"enable_memory\":false,\"input_text\":\"%s\"}", question)
        );
    }

    @Test
    void testApiCallOnError() throws InterruptedException {
        // GIVEN
        final String question = "Hello, how are you";
        server.enqueue(
                new MockResponse()
                        .setStatus("500")
        );

        // WHEN
        WriteSonicResponse response = client.callChatApi(question).block();

        // THEN
        RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod()).isEqualTo(HttpMethod.POST.name());
        assertThat(request.getHeader(API_KEY_HEADER)).isEqualTo(API_KEY);
        assertThat(request.getHeader(HttpHeaders.CONTENT_TYPE)).isEqualTo(HttpHeaderValues.APPLICATION_JSON.toString());
        assertThat(request.getHeader(HttpHeaders.ACCEPT)).isEqualTo(HttpHeaderValues.APPLICATION_JSON.toString());
        assertThat(request.getBody().readString(StandardCharsets.UTF_8)).isEqualTo(
                String.format("{\"enable_google_results\":true,\"enable_memory\":false,\"input_text\":\"%s\"}", question)
        );
        assertThat(response.getMessage()).isEqualTo("[!] An error occurred");
    }
}