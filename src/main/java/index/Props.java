package index;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

final class Props {
	private static Properties props = new Properties();

	static {
		try {
			props.load(new FileInputStream("index.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int getServerPort() {
		var port = props.getProperty("server.port");
		return Integer.valueOf(port);
	}
}
