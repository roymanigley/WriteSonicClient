package ch.bytecrowd.writesonicclient;

import ch.bytecrowd.writesonicclient.client.WriteSonicClient;
import ch.bytecrowd.writesonicclient.model.WriteSonicResponse;
import ch.bytecrowd.writesonicclient.service.ConsoleWriterService;
import ch.bytecrowd.writesonicclient.service.SpringShutdownService;
import ch.bytecrowd.writesonicclient.service.TextToSpeechService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;
import reactor.core.publisher.Mono;

import java.util.Scanner;

import static io.netty.util.internal.StringUtil.EMPTY_STRING;
import static org.mockito.Mockito.*;

class WriteSonicClientApplicationTest {

	@Mock
	WriteSonicClient client;
	@Mock
	TextToSpeechService voice;
	@Mock
	Scanner scanner;
	@Mock
	ConsoleWriterService writer;
	@Mock
	SpringShutdownService shutdownService;
	@Mock
	ApplicationArguments args;

	WriteSonicClientApplication app;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		app = new WriteSonicClientApplication(client, voice, scanner, writer, shutdownService);
	}

	@Test
	void runApplicationWithVoice() throws Exception {
		// GIVEN
		final String question = "Hello";
		final String answer = "Hello there";
		boolean isVoiceEnabled = true;
		when(args.containsOption("voice")).thenReturn(isVoiceEnabled);
		when(scanner.nextLine()).thenReturn(question, EMPTY_STRING);
		when(client.callChatApi(question)).thenReturn(Mono.just(
				WriteSonicResponse.builder()
						.message(answer)
						.build()
		));

		// WHEN
		app.run().run(args);

		// THEN
		verify(voice).speak(answer);
		verify(voice).close();
		verifyNoMoreInteractions(voice);
	}

	@Test
	void runApplicationWithoutVoice() throws Exception {
		// GIVEN
		final String question = "Hello";
		final String answer = "Hello there";
		boolean isVoiceEnabled = false;
		when(args.containsOption("voice")).thenReturn(isVoiceEnabled);
		when(scanner.nextLine()).thenReturn(question, EMPTY_STRING);
		when(client.callChatApi(question)).thenReturn(Mono.just(
				WriteSonicResponse.builder()
						.message(answer)
						.build()
		));

		// WHEN
		app.run().run(args);

		// THEN
		verify(voice).close();
		verifyNoMoreInteractions(voice);
	}

	@Test
	void runApplicationShouldCallClientTwice() throws Exception {
		// GIVEN
		final String question_one = "Hello";
		final String question_two = "How are you?";
		final String answer_one = "Hello there";
		final String answer_two = "I'm fine";
		boolean isVoiceEnabled = false;
		when(args.containsOption("voice")).thenReturn(isVoiceEnabled);
		when(scanner.nextLine()).thenReturn(question_one, question_two, EMPTY_STRING);
		when(client.callChatApi(anyString())).thenReturn(
				Mono.just(
						WriteSonicResponse.builder()
								.message(answer_one)
								.build()),
				Mono.just(
						WriteSonicResponse.builder()
								.message(answer_two)
								.build())
		);

		// WHEN
		app.run().run(args);

		// THEN
		verify(client, times(2)).callChatApi(anyString());
		verifyNoMoreInteractions(client);
	}

	@Test
	void checkThatScannerIsClosed() throws Exception {
		// GIVEN
		final String question = "Hello";
		final String answer = "Hello there";
		boolean isVoiceEnabled = false;
		when(args.containsOption("voice")).thenReturn(isVoiceEnabled);
		when(scanner.nextLine()).thenReturn(question, EMPTY_STRING);
		when(client.callChatApi(anyString())).thenReturn(
				Mono.just(
						WriteSonicResponse.builder()
								.message(answer)
								.build())
		);

		// WHEN
		app.run().run(args);

		// THEN
		verify(scanner).close();
	}

	@Test
	void checkThatApplicationShutdownIsCalled() throws Exception {
		// GIVEN
		final String question = "Hello";
		final String answer = "Hello there";
		boolean isVoiceEnabled = false;
		when(args.containsOption("voice")).thenReturn(isVoiceEnabled);
		when(scanner.nextLine()).thenReturn(question, EMPTY_STRING);
		when(client.callChatApi(anyString())).thenReturn(
				Mono.just(
						WriteSonicResponse.builder()
								.message(answer)
								.build())
		);

		// WHEN
		app.run().run(args);

		// THEN
		verify(shutdownService).shutdown();
	}
}
