package mvc;


import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	public void add(Shape s) {
		if(s != null)
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
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public int size() {
		return shapes.size();
	}
	public void set(int index, Shape shape) {
		shapes.set(index, shape);
	}
	public int indexOf(Shape shape) {
		return shapes.indexOf(shape);
	}
}
