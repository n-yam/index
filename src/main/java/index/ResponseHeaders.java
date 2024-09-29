package index;

class ResponseHeaders {
	private int status;
	private int contentLength;
	private String contentType;
	
	ResponseHeaders(int status, int contentLength, String contentType) {
		this.status = status;
		this.contentLength = contentLength;
		this.contentType = contentType;
	}
	
	String getContentType() {
		return contentType;
	}

	@Override
	public String toString() {
		var status = String.valueOf(this.status);
		var length = String.valueOf(contentLength);
		var headers = """
				HTTP/1.1 #STATUS#
				Server: index
				Connection: close
				Content-Type: #CONTENT_TYPE#
				Content-Length: #LENGTH#
				"""
				.replaceFirst("#STATUS#", status)
				.replaceFirst("#CONTENT_TYPE#", contentType)
				.replaceFirst("#LENGTH#", length);

		return headers;
	}
}
