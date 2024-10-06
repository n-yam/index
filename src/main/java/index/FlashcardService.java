package index;

import java.util.List;

final class FlashcardService {
	private IFlashcardRepository flashcardRepository;

	FlashcardService(IFlashcardRepository flashcardRepository) {
		this.flashcardRepository = flashcardRepository;
	}

	List<Flashcard> getAll() {
		return flashcardRepository.findAll();
	}
}
