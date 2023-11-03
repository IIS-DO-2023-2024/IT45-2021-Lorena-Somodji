package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {
	DrawingModel model = new DrawingModel();
	

	//izuzetak od pocetne mvc arhitekture
	//model je na pocetku null vrednost
	//DrawingModel model;
	//DrawingModel model = new DrawingModel();

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g, false);
		}
	//ovde ne treba da se nalazi repaint
	}
}
