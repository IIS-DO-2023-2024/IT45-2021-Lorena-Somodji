package geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shape {
	
	private Dot upperLeftPoint;
	private int height; 
	private int width; 
	private Color colorFill;

	public Rectangle () 
	{
		this.upperLeftPoint = new Dot();
	}

	public Rectangle(Dot one, int height, int width, Color colorDrive, Color colorFill) throws Exception 
	{		
		if(one != null && height>0 && width>0) 
		{
			this.upperLeftPoint = one;
			this.height = height;
			this.width = width;
			this.colorDrive=colorDrive;
			this.colorFill=colorFill;
			this.selected=false;
			this.shape = Shapes.RECTANGLE;
		}else 
		{
			if(one == null) throw new Exception("Invalid Points");
			if(height<=0) throw new Exception("Pogresna visina!");
			if(width<=0) throw new Exception("Pogresna sirina!");
		}
	}
	
	public double area() {
		return (this.height * this.width);
	}

	public double circumference() {
		return 2*(this.height + this.width);
	}

	public Dot getUpperLeftPoint() {
		return this.upperLeftPoint;
	}

	public void setUpperLeftPoint(Dot upperLeftPoint) throws Exception 
	{
		this.upperLeftPoint = new Dot(upperLeftPoint.getX(),upperLeftPoint.getY());
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) throws Exception {
		if(height > 0) {
			this.height = height;
		}
		else {
			throw new Exception ("Visina mora biti pozitivna!");
		}
		
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) throws Exception {
		if(width > 0) {
			this.width = width;
		}
		else {
			throw new Exception ("Sirina mora biti pozitivna!");
		}
	}
	
	public Color getColorFill()
	{
		return this.colorFill;
	}
	
	public void setColorFill(Color colorFill)
	{
		this.colorFill=colorFill;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeftPoint=" + upperLeftPoint + ", width=" + width + ", height=" + height
				+ ", colorDrive=" + colorDrive + ", colorFill=" + colorFill + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Rectangle) {
			Rectangle r = (Rectangle)o; 
			return (r.getUpperLeftPoint().equals(this.upperLeftPoint) &&
					r.getHeight() == this.height && r.getWidth() == this.width);
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(int x, int y) {
		if(x > this.upperLeftPoint.getX() && x < this.upperLeftPoint.getX() + width
				&& y > this.upperLeftPoint.getY() && y < this.getUpperLeftPoint().getY() + height) {
			return true;
		}
		return false;
	}

	public void moveBy(int byX, int byY) {///pomera ya x y 
		((Moveable) this.upperLeftPoint).moveBy(byX, byY);
	}

	public void moveTo(int x, int y) {//premesti
		((Moveable) this.upperLeftPoint).moveTo(x, y);
	}

	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}

	@Override
	public void draw(Graphics g, boolean selected) {
		if(g!=null) {
			if(selected) 
			{	
				g.setColor(Color.BLACK);
				g.fillRect(getUpperLeftPoint().getX()-4, getUpperLeftPoint().getY()-4, getWidth()+10, height+10);
				g.setColor(Color.CYAN);
				g.fillRect(getUpperLeftPoint().getX()-3, getUpperLeftPoint().getY()-3, getWidth()+8, height+8);
				g.fillRect(getUpperLeftPoint().getX()-2, getUpperLeftPoint().getY()-2, getWidth()+5, height+5);
				g.setColor(Color.BLACK);
				g.fillRect(getUpperLeftPoint().getX()-1, getUpperLeftPoint().getY()-1, getWidth()+2, height+2);
			
			}

			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(this.getColorDrive());
			g2.setStroke(new BasicStroke(3f));
			g2.setColor(this.colorDrive);
			g.drawRect(getUpperLeftPoint().getX(), getUpperLeftPoint().getY(), getWidth(), height);
			g.setColor(this.colorFill); 
			g.fillRect(getUpperLeftPoint().getX(), getUpperLeftPoint().getY(), getWidth(), height);
		}
	}

	@Override
	public boolean contains(Dot one) {
		int x = one.getX();
		int y = one.getY();
		
		if(x > this.getUpperLeftPoint().getX() && x < this.getUpperLeftPoint().getX() + getWidth()
			&& y > this.getUpperLeftPoint().getY() && y < this.getUpperLeftPoint().getY() + height) {
		
			return true;
		}
		
		return false;
	}

	@Override
	public void clone(Shape shape) throws Exception {
		if (shape instanceof Rectangle) {
			super.clone(shape);
			Rectangle r = (Rectangle) shape;
			this.upperLeftPoint.clone(r.upperLeftPoint);
			this.setWidth(r.width);
			this.setHeight(r.height);
			this.setColorFill(r.colorFill);
		}
	}
}

