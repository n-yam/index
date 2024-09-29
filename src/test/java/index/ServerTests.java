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
	void test() {
		try {
			var client = HttpClient.newHttpClient();
			var uri = URI.create("http://localhost:8000");
			var request = HttpRequest.newBuilder().uri(uri).build();
			var response = client.send(request, BodyHandlers.ofString());

			assertEquals(200, response.statusCode());
			assertEquals("<h1>HELLO WORLD</h1>", response.body());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
