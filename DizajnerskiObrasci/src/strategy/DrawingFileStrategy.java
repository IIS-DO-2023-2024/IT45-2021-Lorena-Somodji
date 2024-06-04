package strategy;

import java.util.ArrayList;

import geometry.Shape;

public interface DrawingFileStrategy {
	public ArrayList<Shape> load(String path);
	public void save(ArrayList<Shape> shapes, String path);
}
