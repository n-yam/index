package index;

final class ResponseBuilder {
	private int status;
	private String body;
	private String contentType;

	ResponseBuilder status(int status) {
		this.status = status;
		return this;
	}

	ResponseBuilder body(String body) {
		this.body = body;
		return this;
	}

	ResponseBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	Response build() {
		return new Response(status, body, contentType);
	}
}
