package command;

import geometry.Shape;

public class DeselectShapeCmd implements Command {
	
	private Shape shape;

	public DeselectShapeCmd(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
	}

	@Override
	public String toString() {
		return "Deselect [shape=" + shape + "]";
	}

}