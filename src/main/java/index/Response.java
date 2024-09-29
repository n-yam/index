package index;

class Response {
	private int status;
	private String body;
	private String contentType;
	
	Response(int status, String body, String contentType) {
		this.status = status;
		this.body = body;
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		var status = String.valueOf(this.status);
		var length = String.valueOf(body.length());
		var response = """
				HTTP/1.1 #STATUS#
				Server: index
				Connection: close
				Content-Type: #CONTENT_TYPE#
				Content-Length: #LENGTH#

				#HTML#
				"""
				.replaceFirst("#STATUS#", status)
				.replaceFirst("#CONTENT_TYPE#", contentType)
				.replaceFirst("#LENGTH#", length)
				.replaceFirst("#HTML#", body);

		return response;
	}
}
