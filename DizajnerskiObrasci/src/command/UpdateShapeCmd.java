package command;

import javax.swing.JOptionPane;

import geometry.Circle;
import geometry.Donut;
import geometry.Dot;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Rectangle;
import geometry.Shape;

public class UpdateShapeCmd implements Command {
	
	private Shape shape;
	private Shape newState;
	private Shape original;

	public UpdateShapeCmd(Shape shape, Shape newState) throws Exception {
		if(shape.getShape() != newState.getShape()) {
			throw new Exception("Shapes must be same type!");
		}
		this.shape = shape;
		this.newState = newState;
		switch (shape.getShape()) {
		case DOT:
			original = new Dot();
			break;
		case LINE:
			original = new Line();
			break;
		case RECTANGLE:
			original = new Rectangle();
			break;
		case CIRCLE:
			original = new Circle();
			break;
		case DONUT:
			original = new Donut();
			break;
		case HEXAGON:
			original = new HexagonAdapter();
			break;
		}
	}

	@Override
	public void execute() {
		try {
			original.clone(shape);
			shape.clone(newState);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void unexecute() {
		try {
			shape.clone(original);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
