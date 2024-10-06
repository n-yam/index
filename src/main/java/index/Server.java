package index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;

final class Server {
	private int port;
	private FlashcardService flashcardService;

	public Server(int port, IFlashcardRepository flashcardRepository) {
		this.port = port;
		this.flashcardService = new FlashcardService(flashcardRepository);
	}

	public void start() {
		try (var serverSocket = new ServerSocket(port)) {
			while (true) {
				try (var clientSocket = serverSocket.accept();
						var inputStream = clientSocket.getInputStream();
						var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

					var request = RequestParser.parse(bufferedReader);
					var writer = new ResponseWriter(clientSocket);

					if (request.getUri().equals("/")) {
						var body = getResourceAsString("static/index.html");
						var headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/html").build();
						writer.write(headers, body);
						continue;
					}
					
					if (request.getUri().equals("/main.js")) {
						var body = getResourceAsString("static/main.js");
						var headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/javascript").build();
						writer.write(headers, body);
						continue;
					}
					
					if (request.getUri().equals("/styles.css")) {
						var body = getResourceAsString("static/styles.css");
						var headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/css").build();
						writer.write(headers, body);
						continue;
					}
					
					if (request.getUri().equals("/favicon.ico")) {
						var body = getResourceAsStream("static/favicon.ico");
						var headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(15406)
								.contentType("image/x-icon").build();
						writer.write(headers, body);
						continue;
					}
					
					if (request.getUri().equals("/api/flashcards")) {
						var flashcards = flashcardService.getAll();
						var body = Json.dumps(flashcards);
						var headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("application/json").build();
						writer.write(headers, body);
						continue;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getResourceAsString(String path) throws IOException {
		var inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		var content = new String(inputStream.readAllBytes());
		return content;
	}
	
	private InputStream getResourceAsStream(String path) throws IOException {
		return this.getClass().getClassLoader().getResourceAsStream(path);
	}
}
