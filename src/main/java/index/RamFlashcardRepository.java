package index;

import java.util.ArrayList;
import java.util.List;

final class RamFlashcardRepository implements IFlashcardRepository {

	private List<Flashcard> flashcards = new ArrayList<>();
	
	@Override
	public void add(Flashcard flashcard) {
		flashcards.add(flashcard);
	}

	@Override
	public List<Flashcard> findAll() {
		return flashcards;
	}
}
