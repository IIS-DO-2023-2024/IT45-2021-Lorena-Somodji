package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	
	public ToFrontCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), model.indexOf(shape), model.indexOf(shape) + 1);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), model.indexOf(shape), model.indexOf(shape) - 1);
	}

	@Override
	public String toString() {
		return "To Front [shape=" + shape + "]";
	}

}
