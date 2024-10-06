package index;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class JsonTests {
	@Test
	void dumps() {
		var flashcard01 = new Flashcard(1L, "FRONT01", "BACK01");
		var flashcard02 = new Flashcard(2L, "FRONT02", "BACK02");
		var flashcards = List.of(flashcard01, flashcard02);
		var json = Json.dumps(flashcards);
		var expected = """
				[\
				{"id":1,"frontText":"FRONT01","backText":"BACK01"},\
				{"id":2,"frontText":"FRONT02","backText":"BACK02"}\
				]\
				""";
		assertEquals(expected, json);
	}
}
