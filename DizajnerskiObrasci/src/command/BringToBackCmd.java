package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int oldIndex;
	
	public BringToBackCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		this.oldIndex = model.indexOf(shape);
		model.remove(shape);
		model.add(0, shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.add(oldIndex, shape);
	}

	@Override
	public String toString() {
		return "Bring To Back [shape=" + shape + "]";
	}

}
