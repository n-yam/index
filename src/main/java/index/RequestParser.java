package index;

import java.io.BufferedReader;
import java.io.IOException;

final class RequestParser {
	static Request parse(BufferedReader reader) throws IOException {
		var method = "";
		var uri = "";
		var body = "";

		var line = "";
		var lineNumber = 1;

		while ((line = reader.readLine()) != null) {
			if (lineNumber == 1) {
				var requestLine = line.split("\s");
				method = requestLine[0];
				uri = requestLine[1];
			}

			if (line.equals(""))
				break;

			lineNumber++;
		}

		return new Request(method, uri, body);
	}
}
