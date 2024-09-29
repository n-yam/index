package index;

final class ResponseHeadersBuilder {
	private int status;
	private int contentLength;
	private String contentType;

	ResponseHeadersBuilder status(int status) {
		this.status = status;
		return this;
	}

	ResponseHeadersBuilder contentLength(int contentLength) {
		this.contentLength = contentLength;
		return this;
	}

	ResponseHeadersBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	ResponseHeaders build() {
		return new ResponseHeaders(status, contentLength, contentType);
	}
}
