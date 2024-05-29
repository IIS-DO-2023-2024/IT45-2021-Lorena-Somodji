package mvc;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JOptionPane;

import command.AddShapeCmd;
import command.Command;
import command.DeselectShapeCmd;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.UpdateShapeCmd;
import geometry.Dot;
import geometry.Line;
import geometry.Shape;
import geometry.Shapes;
import paint.Dialog;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private Dialog dialog = new Dialog(); 
	
	private String currentShape = Shapes.DOT.toString();
	private String prevSelected;
	private Dot one, two;
	
	private Shape selectedShape;
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	public DrawingController (DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
	}
	
	public boolean getSelect()
	{
		return this.selectedShape != null;
	}
	
	private void execute(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
		frame.repaint();
	}
	public void undo() {
		if(undoStack.empty()) {
			JOptionPane.showMessageDialog(null, "Nothing to undo!");
			return;
		}
		Command cmd = undoStack.pop();
		cmd.unexecute();
		redoStack.push(cmd);
		frame.repaint();
	}
	public void redo() {
		if(redoStack.empty()) {
			JOptionPane.showMessageDialog(null, "Nothing to redo!");
			return;
		}
		Command cmd = redoStack.pop();
		cmd.execute();
		undoStack.push(cmd);
		frame.repaint();
	}
	
	private void select(Dot one) {
		for(int i = model.size()-1; i>=0; i--)
		{
			Shape shape = model.get(i);
			if(shape.contains(one)) 
			{
				execute(new SelectShapeCmd(shape));
				selectedShape = shape;
				return;
			}
		}
		this.deselect();
	}
	
	private void deselect() {
		for(Shape s : model.getShapes()) {
			if(s.isSelected()) execute(new DeselectShapeCmd(s));
		}
		this.selectedShape = null;
	}
	
	private void AddObject() 
	{
		try {
			Shape shape = null;
			if(currentShape == Shapes.DOT.toString()) 
			{ 
				shape = new Dot(one.getX(),one.getY(),frame.getColor());
			} else if(currentShape == Shapes.LINE.toString()) 
			{
				if(one != null && two != null) shape = new Line(one,two,frame.getColor());
			}else if(currentShape == Shapes.RECTANGLE.toString()) 
			{
				shape = dialog.showDialog( frame.getColor(), one, Shapes.RECTANGLE);
			}else if(currentShape == Shapes.CIRCLE.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), one, Shapes.CIRCLE);
			}else if(currentShape == Shapes.DONUT.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), one, Shapes.DONUT);
			}else if(currentShape == Shapes.HEXAGON.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), one, Shapes.HEXAGON);
			}
			
			if(shape != null) {
				execute(new AddShapeCmd(shape, model));
			}
		}catch(Exception e) { JOptionPane.showMessageDialog(null, e, "Greska", 2); }
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
		{
			this.prevSelected = this.currentShape;
			this.currentShape = frame.selectedButton();
			
			if(getSelect()) 
			{ 
				this.deselect();
				return;
			}
			
			if(currentShape == Shapes.LINE.toString() && prevSelected == currentShape) {
				if(two!=null) two = null; 
				else two = one; 
			}else two = null; 
			
			
			Point p = e.getPoint();
			try { 
				one = new Dot(p.x,p.y);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} 
			
			AddObject();
		}else if(e.getButton() == MouseEvent.BUTTON3) 
		{
			Point p = e.getPoint();
			if(p != null) {
				try {
					one = new Dot(p.x,p.y);
					deselect();
					select(one); 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		}
	}
	
	public void delete() {
		if(selectedShape != null) 
		{
			execute(new RemoveShapeCmd(selectedShape, model));
			selectedShape = null;
		}
	}
	
	public void edit() {
		if(selectedShape != null) 
		{
			Shape obl =  dialog.showDialog(selectedShape);
			if(obl!=null) {
				try {
					execute(new UpdateShapeCmd(selectedShape, obl));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

	
}
