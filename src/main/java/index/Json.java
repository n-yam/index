package index;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

final class Json {
	static String dumps(Collection<?> collection) {
		String json = collection.stream().map(object -> {
			return dumps((Flashcard) object);
		}).collect(Collectors.joining(",", "[", "]"));
		return json;
	}

	static String dumps(Flashcard flashcard) {
		Field[] fields = flashcard.getClass().getDeclaredFields();
		String json = Arrays.stream(fields).map(field -> {
			try {
				field.setAccessible(true);
				String name = field.getName();
				Object value = field.get(flashcard);

				if (value instanceof Number)
					return "\"#NAME#\":#VALUE#"
							.replaceFirst("#NAME#", name)
							.replaceFirst("#VALUE#", String.valueOf(value));
				
				return "\"#NAME#\":\"#VALUE#\""
						.replaceFirst("#NAME#", name)
						.replaceFirst("#VALUE#", String.valueOf(value));

			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return "";
			}
		}).collect(Collectors.joining(",", "{", "}"));

		return json;
	}
}
