package index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.Test;

class ServerTests {
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
}
