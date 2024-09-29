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
						var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

					var request = RequestParser.parse(bufferedReader);

					var body = "<p>NOT FOUND</p>";
					var headers = new ResponseHeaders(404, body.length(), "text/html");
					
					if (request.getUri().equals("/")) {
						body = readStaticFile("static/index.html");
						headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/html").build();
					}
					
					if (request.getUri().equals("/main.js")) {
						body = readStaticFile("static/main.js");
						headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/javascript").build();
					}
					
					if (request.getUri().equals("/")) {
						body = readStaticFile("static/index.html");
						headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/html").build();
					}
					
					if (request.getUri().equals("/styles.css")) {
						body = readStaticFile("static/styles.css");
						headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(body.length())
								.contentType("text/css").build();
					}
					
					if (request.getUri().equals("/favicon.ico")) {
						headers = new ResponseHeadersBuilder()
								.status(200)
								.contentLength(15406)
								.contentType("image/x-icon").build();
					}
					
					var outputStream = clientSocket.getOutputStream();
					var bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
					
					try {
						// Write headers
						bufferedWriter.write(headers.toString());
						bufferedWriter.write("\r\n");
						bufferedWriter.flush();

						if (headers.getContentType().equals("image/x-icon")) {
							// Write binary body
							try (var is = new ClassLoader(){}.getResourceAsStream("static/favicon.ico")){
								is.transferTo(outputStream);	
							}
						} else {
							// Write string body
							bufferedWriter.write(body.toString());
							bufferedWriter.flush();
						}
						
					} finally {
						bufferedWriter.close();
						outputStream.close();
					}
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
