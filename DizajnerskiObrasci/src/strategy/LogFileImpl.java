package strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogFileImpl implements LogFileStrategy {

	@Override
	public String load(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void save(String log, String path) {
		try {
			Files.write(Paths.get(path), log.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
