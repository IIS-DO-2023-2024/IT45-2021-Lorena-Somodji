package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Serializable { 
	protected boolean selected; 
	protected Shapes shape;  
	protected  Color colorDrive; 
	
	public Shape() {

	}

	public boolean isSelected() { 
		return this.selected;
	}

	public void setSelected(boolean selected) { 
		this.selected = selected;
	}
	
	public abstract void draw(Graphics g, boolean selected); 
	public abstract boolean contains(int x, int y);
	public abstract boolean contains(Dot one); 
	
	public Shapes getShape()
	{
		return this.shape; 
	}
  
	public Color getColorDrive() 
	{
		return this.colorDrive;
	}
	
	public void setColorDrive(Color colorDrive)
	{
		this.colorDrive=colorDrive;
	}
	
	public boolean getSelected()
	{
		return this.selected;
	}
	
	public void clone(Shape shape) throws Exception {
		this.colorDrive = shape.colorDrive;
	}
}
