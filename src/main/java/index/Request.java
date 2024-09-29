package index;

final class Request {
	private String method;
	private String uri;
	private String body;

	Request(String method, String uri, String body) {
		this.method = method;
		this.uri = uri;
		this.body = body;
	}

	String getMethod() {
		return method;
	}

	String getUri() {
		return uri;
	}
	
	String getBody() {
		return body;
	}
}
