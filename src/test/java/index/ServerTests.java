package index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ServerTests {
	static IFlashcardRepository flashcardRepository = new RamFlashcardRepository();
	
	@BeforeAll
	static void beforeAll() {
		var server = new Server(8000, flashcardRepository);
		var thread = new Thread(() -> server.start());
		thread.setDaemon(true);
		thread.start();

		try {
			Thread.sleep(100); // milliseconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void getIndexHtml() {
		try {
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("text/html", response.headers().firstValue("Content-Type").get());
			assertEquals(213, response.headers().firstValueAsLong("Content-Length").getAsLong());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void getMainJs() {
		try {
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000/main.js");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("text/javascript", response.headers().firstValue("Content-Type").get());
			assertEquals(99, response.headers().firstValueAsLong("Content-Length").getAsLong());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void getStylesCss() {
		try {
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000/styles.css");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("text/css", response.headers().firstValue("Content-Type").get());
			assertEquals(53, response.headers().firstValueAsLong("Content-Length").getAsLong());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void getFaviconIco() {
		try {
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000/favicon.ico");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("image/x-icon", response.headers().firstValue("Content-Type").get());
			assertEquals(15406, response.headers().firstValueAsLong("Content-Length").getAsLong());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void getAllFlashcards() {
		try {
			var flashcard01 = new Flashcard(1, "FRONT01", "BACK01");
			var flashcard02 = new Flashcard(2, "FRONT02", "BACK02");
			flashcardRepository.add(flashcard01);
			flashcardRepository.add(flashcard02);
			
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000/api/flashcards");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("application/json", response.headers().firstValue("Content-Type").get());
			assertEquals(response.body().length(), response.headers().firstValueAsLong("Content-Length").getAsLong());
			
			var json = """
					[{"id":1,"frontText":"FRONT01","backText":"BACK01"},\
					{"id":2,"frontText":"FRONT02","backText":"BACK02"}]\
					""";
			
			assertEquals(json, response.body());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
