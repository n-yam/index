package index;

final class Part {
	private String name;
	private String value;

	Part(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public final String getName() {
		return name;
	}

	public final String getValue() {
		return value;
	}
}
