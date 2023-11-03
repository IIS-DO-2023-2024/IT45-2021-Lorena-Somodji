package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;

import geometry.Dot;
import geometry.Shape;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController (DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
	}
	
	public void mouseCllicked(MouseEvent e) {
		Dot p = new Dot(e.getX(), e.getY(), Color.RED);
		model.add(p);
		frame.repaint();
		System.out.println(System.currentTimeMillis());
	}
}
