package index;

import java.io.IOException;
import java.util.Properties;

final class Props {
	private static Properties props = new Properties();
	private static ClassLoader classLoader= new ClassLoader(){};

	static {
		try {
			props.load(classLoader.getResourceAsStream("index.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int getServerPort() {
		var port = props.getProperty("server.port");
		return Integer.valueOf(port);
	}
}
