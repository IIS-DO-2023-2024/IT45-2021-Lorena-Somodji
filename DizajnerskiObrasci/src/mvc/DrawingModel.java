package mvc;


import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	public void add(Shape s) {
		shapes.add(s);
	}
	public void remove (Shape s) {
		shapes.remove(s);
	}
	public Shape get (int index) {
		return shapes.get(index);
	}
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
