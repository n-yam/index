package index;

final class Main {
	public static void main(String[] args) {
		int port = Props.getServerPort();
		IFlashcardRepository flashcardRepository = new RamFlashcardRepository();
		var server = new Server(port, flashcardRepository);
		server.start();
	}
}
