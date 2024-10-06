package index;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

final class RequestParser {
	static Request parse(BufferedReader reader) throws IOException {
		var method = "";
		var uri = "";
		var contentType = "";
		var contentLength = 0;
		var boundary = "";
		var body = "";
		var parts = new ArrayList<Part>();

		var line = "";
		var lineNumber = 0;

		while ((line = reader.readLine()) != null && !line.equals("")) {
			lineNumber++;
			
			if (lineNumber == 1) {
				var requestLine = line.split("\s");
				method = requestLine[0];
				uri = requestLine[1];
				continue;
			}

			if (line.startsWith("Content-Type")) {
				contentType = line.substring("Conetent-Type:".length(), line.length());
			}
			
			if (line.startsWith("Content-Length")) {
				contentLength = Integer.valueOf(line.substring("Conetent-Length:".length(), line.length()));
			}
		}
		
		if (contentType.startsWith("multipart/form-data")) {
			boundary = "--" + contentType.substring("multipart/form-data; boundary=".length(), contentType.length());

			var length = 0;
			var name = "";
			var value = "";
			
			while (length < contentLength && (line = reader.readLine()) != null) {	
				length += line.length() + 2; // 2 = CRLF

				if (line.startsWith("Content-Disposition")) {
					name = line.substring("Content-Disposition: form-data; name=\"".length(), line.length() - 1); // -1 = "

				} else if (!line.equals("") && !line.startsWith(boundary)) {
					value = line;
				}
					
				if (!name.equals("") && !value.equals("") && line.startsWith(boundary)) {
					parts.add(new Part(name, value));
					name = "";
					value = "";
				}
			}
		}
		
		return new Request(method, uri, contentType, contentLength, boundary, parts, body);
	}
}
