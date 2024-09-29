package index;

class Response {
	private int status;
	private String body;

	public Response(int status, String body) {
		this.status = status;
		this.body = body;
	}

	@Override
	public String toString() {
		var status = String.valueOf(this.status);
		var length = String.valueOf(body.length());
		var response = """
				HTTP/1.1 #STATUS#
				Server: index
				Connection: close
				Content-Type: text/html
				Content-Length: #LENGTH#

				#HTML#
				""".replaceFirst("#STATUS#", status).replaceFirst("#LENGTH#", length).replaceFirst("#HTML#", body);
		return response;
	}
}
