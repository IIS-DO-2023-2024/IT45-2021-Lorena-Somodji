package strategy;

public interface LogFileStrategy {
	public String load(String path);
	public void save(String log, String path);
}
