package mvc;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	public DrawingController (DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
	}
	
	public boolean getSelect()
	{
		return this.selectedShape != null;
	}
	
	private void select(Dot one) {
		for(int i = model.size()-1; i>=0; i--)
		{
			Shape shape = model.get(i);
			if(shape.contains(one)) 
			{
				shape.setSelected(true);
				selectedShape = shape;
				frame.repaint();
				return;
			}
		}
		this.deselect();
		frame.repaint();
	}
	
	private void deselect() {
		for(Shape s : model.getShapes()) {
			s.setSelected(false);
		}
		this.selectedShape = null;
		frame.repaint();
	}
	
	private void AddObject() 
	{
		try {
			if(currentShape == Shapes.DOT.toString()) 
			{ 
				model.add(new Dot(one.getX(),one.getY(),frame.getColor()));
			} else if(currentShape == Shapes.LINE.toString()) 
			{
				if(one != null && two != null) model.add(new Line(one,two,frame.getColor()));
			}else if(currentShape == Shapes.RECTANGLE.toString()) 
			{
				model.add(dialog.showDialog( frame.getColor(), one, Shapes.RECTANGLE));
			}else if(currentShape == Shapes.CIRCLE.toString()) 
			{
				model.add(dialog.showDialog(frame.getColor(), one, Shapes.CIRCLE));
			}else if(currentShape == Shapes.DONUT.toString()) 
			{
				model.add(dialog.showDialog(frame.getColor(), one, Shapes.DONUT));
			}
			frame.repaint();
		}catch(Exception e) { JOptionPane.showMessageDialog(null, e, "Greska", 2); }
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
		{
			this.prevSelected = this.currentShape;
			this.currentShape = frame.SelektovanoDugme();
			
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
			model.remove(selectedShape);
			selectedShape = null;
			frame.repaint();
		}
	}
	
	public void edit() {
		if(selectedShape != null) 
		{
			Shape obl =  dialog.showDialog(selectedShape);
			if(obl!=null) {
				model.set(model.indexOf(selectedShape),obl);
			}
			
			frame.repaint();
		}
	}
}
