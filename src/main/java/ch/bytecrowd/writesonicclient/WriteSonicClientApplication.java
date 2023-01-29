package ch.bytecrowd.writesonicclient;

import ch.bytecrowd.writesonicclient.client.WriteSonicClient;
import ch.bytecrowd.writesonicclient.model.WriteSonicResponse;
import ch.bytecrowd.writesonicclient.service.ConsoleWriterService;
import ch.bytecrowd.writesonicclient.service.SpringShutdownService;
import ch.bytecrowd.writesonicclient.service.TextToSpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.function.Consumer;

@SpringBootApplication
@RequiredArgsConstructor
public class WriteSonicClientApplication {

	static ConfigurableApplicationContext context;
	final WriteSonicClient client;
	final TextToSpeechService voice;
	final Scanner scanner;
	final ConsoleWriterService writer;
	final SpringShutdownService shutdownService;

	public static void main(String[] args) {
		context = SpringApplication.run(WriteSonicClientApplication.class, args);
	}

	@Bean
	ApplicationRunner run() {
		return (args) -> proceedQuestion(
				args.containsOption("voice")
		);
	}

	void proceedQuestion(final boolean withVoice) {
		writer.print("[+] Enter your question: \n> ");
		String question = scanner.nextLine();
		if (!question.isBlank()) {
			writer.println("[+] Processing question: " + question);
			client.callChatApi(question)
					.doOnNext(answer -> writer.println(answer.toPrint()))
					.map(WriteSonicResponse::getMessage)
					.doOnNext(handleVoice(withVoice))
					.subscribe(s -> proceedQuestion(withVoice));
		} else {
			writer.println("[+] Quiting");
			shutdown(scanner);
		}
	}

	private Consumer<String> handleVoice(boolean withVoice) {
		return answer -> {
			if (withVoice) voice.speak(answer);
		};
	}

	void shutdown(Scanner scanner) {
		voice.close();
		scanner.close();
		shutdownService.shutdown();
	}
}
