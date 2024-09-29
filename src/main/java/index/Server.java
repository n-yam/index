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

					var response = """
							HTTP/1.1 200
							Server: index
							Content-Type: text/html
							Connection: close
							Content-Length: 20
							
							<h1>HELLO WORLD</h1>
							""";
					bufferedWriter.write(response);
					bufferedWriter.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
