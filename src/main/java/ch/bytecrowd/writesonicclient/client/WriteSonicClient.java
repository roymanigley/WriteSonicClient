package ch.bytecrowd.writesonicclient.client;

import ch.bytecrowd.writesonicclient.model.WriteSonicRequest;
import ch.bytecrowd.writesonicclient.model.WriteSonicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WriteSonicClient {

    private final WebClient webClient;

    public Mono<WriteSonicResponse> callChatApi(String question) {
        return webClient.post()
                .bodyValue(
                        WriteSonicRequest.builder()
                                .enable_google_results(true)
                                .enable_memory(false)
                                .input_text(question)
                                .build()
                )
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(WriteSonicResponse.class))
                .doOnError(throwable -> System.out.println("[!] " + throwable.getMessage()))
                .onErrorReturn(
                        WriteSonicResponse.builder()
                        .message("[!] An error occurred")
                        .build()
                );
    }
}