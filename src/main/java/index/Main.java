package index;

final class Main {
	public static void main(String[] args) {
		int port = Props.getServerPort();
		var server = new Server(port);
		server.start();
	}
}
