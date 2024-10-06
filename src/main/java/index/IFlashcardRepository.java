package index;

import java.util.List;

interface IFlashcardRepository {
	void add(Flashcard flashcard);

	List<Flashcard> findAll();
}
