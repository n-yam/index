package index;

import java.util.List;

final class Request {
	private String method;
	private String uri;
	private String contentType;
	private int contentLength;
	private String boundary;
	private List<Part> parts;
	private String body;

	Request(String method, String uri, String contentType, int contentLength, String boundary, List<Part> parts, String body) {
		this.method = method;
		this.uri = uri;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.boundary = boundary;
		this.parts = parts;
		this.body = body;
	}

	String getMethod() {
		return method;
	}

	String getUri() {
		return uri;
	}
	
	String getContentType() {
		return contentType;
	}
	
	int getContentLength() {
		return contentLength;
	}
	
	String getBoundary() {
		return boundary;
	}
	
	List<Part> getParts() {
		return parts;
	}
	
	String getBody() {
		return body;
	}
}
