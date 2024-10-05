package index;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

final class ResponseWriter {

	Socket clientSocket;

	ResponseWriter(Socket socket) {
		clientSocket = socket;
	}

	void write(ResponseHeaders headers, String body) throws IOException {
		try (var outputStream = clientSocket.getOutputStream();
				var bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {

			bufferedWriter.write(headers.toString());
			bufferedWriter.write("\r\n");
			bufferedWriter.write(body.toString());
			bufferedWriter.flush();
		}
	}

	void write(ResponseHeaders headers, InputStream inputStream) throws IOException {
		try (var outputStream = clientSocket.getOutputStream();
				var bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {

			bufferedWriter.write(headers.toString());
			bufferedWriter.write("\r\n");
			bufferedWriter.flush();
			inputStream.transferTo(outputStream);
		}
	}
}
