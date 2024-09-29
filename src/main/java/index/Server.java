package index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

final class Server {
	private int port;

	public Server(int port) {
		this.port = port;
	}

	public void start() {
		try (var serverSocket = new ServerSocket(port)) {
			while (true) {
				try (var clientSocket = serverSocket.accept();
						var inputStream = clientSocket.getInputStream();
						var outputStream = clientSocket.getOutputStream();
						var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
						var bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {

					var request = RequestParser.parse(bufferedReader);
					var response = new Response(404, "<p>NOT FOUND</p>", "text/html");
					
					if (request.getUri().equals("/")) {
						response = new ResponseBuilder()
								.status(200)
								.body(readStaticFile("static/index.html"))
								.contentType("text/html").build();
					}
					
					if (request.getUri().equals("/main.js")) {
						response = new ResponseBuilder()
								.status(200)
								.body(readStaticFile("static/main.js"))
								.contentType("text/javascript").build();
					}
					
					if (request.getUri().equals("/")) {
						response = new ResponseBuilder()
								.status(200)
								.body(readStaticFile("static/index.html"))
								.contentType("text/html").build();
					}
					
					if (request.getUri().equals("/styles.css")) {
						response = new ResponseBuilder()
								.status(200)
								.body(readStaticFile("static/styles.css"))
								.contentType("text/css").build();
					}

					bufferedWriter.write(response.toString());
					bufferedWriter.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readStaticFile(String path) throws IOException {
		var classLoader = new ClassLoader() {
			String getResourceAsString(String name) throws IOException {
				var inputStream = this.getResourceAsStream(name);
				var content = new String(inputStream.readAllBytes());
				return content;
			}
		};
		var content = classLoader.getResourceAsString(path);

		return content;
	}
}
