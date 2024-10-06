package index;

final class Flashcard {
	private long id;
	private String frontText;
	private String backText;

	Flashcard(long id, String frontText, String backText) {
		this.id = id;
		this.frontText = frontText;
		this.backText = backText;
	}
	
	public final long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public final String getFrontText() {
		return frontText;
	}

	public final void setFrontText(String frontText) {
		this.frontText = frontText;
	}

	public final String getBackText() {
		return backText;
	}

	public final void setBackText(String backText) {
		this.backText = backText;
	}
}
