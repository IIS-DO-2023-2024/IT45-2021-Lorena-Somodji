package geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape {
	
	private int radius;
	private Dot center;
	private Color colorFill;

	public Circle() {
		this.center = new Dot();
	}

	public Circle(Dot tacka, int r) throws Exception 
	{
		if(tacka != null && r>0) 
		{
			this.center = tacka;
			this.radius = r;
			this.shape = Shapes.CIRCLE;
			this.selected=false; 
		}else 
		{
			if(tacka == null) throw new Exception("Invalid Points center.");
			else throw new Exception("Pogresan poluprecnik.");
		}
	}
	
	public Circle(Dot tacka, int r,  Color colorDrive, Color colorFill) throws Exception 
	{
		if(tacka != null && r>0) 
		{
			this.center = tacka;
			this.radius = r;
			this.colorDrive=colorDrive;
			this.colorFill = colorFill;
			this.shape = Shapes.CIRCLE;
			this.selected=false;
		}else 
		{
			if(tacka == null) throw new Exception("Invalid Points centra.");
			else throw new Exception("Pogresan poluprecnik.");
			
		}
	}

	public double area() {
		double p = Math.pow(this.getRadius(), 2)*Math.PI;
		return Math.round(p*100.0)/100.0;
	}

	public double circumference() {
		return 2*this.radius*Math.PI;
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) throws Exception {
		if(radius < 0) {
			throw new Exception("Radius ne moze da bude manji od 0");
		}
		this.radius = radius;
	}

	public Dot getCenter() {
		return this.center;
	}

	public void setCenter(Dot center) {
		this.center = center;
	}
	
	public void setCenter(int x, int y)
	{
		try {
			this.center.setX(x);
			this.center.setY(y);
		}
		catch(Exception e){
			
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
		return "Circle [center=" + center + ", radius=" + radius + ", colorDrive=" + colorDrive + ", colorFill="
				+ colorFill + "]";
	}

	@Override
	public boolean equals(Object obj) { 
		if(obj instanceof Circle) {
			Circle c = (Circle)obj;
			if(this.getCenter().equals(c.getCenter()) && this.getRadius() == c.getRadius()) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean contains(int x, int y) { 
		return this.getCenter().distance(x, y) <= this.radius;
	}

	@Override
	public boolean contains(Dot point) {
		return ( this.getCenter().distance(point.getX(), point.getY()) <= this.getRadius() );
	}

	@Override
	public void draw(Graphics g, boolean selected) {
		if(g!=null) {
			if(selected) 
			{
				g.setColor(Color.BLACK);
				g.fillOval((int)getCenter().getX() - getRadius()-3, (int)getCenter().getY()-getRadius()-3, getRadius()*2+6, getRadius()*2+6);
				g.setColor(Color.CYAN);
				g.fillOval((int)getCenter().getX()-getRadius()-2, (int)getCenter().getY()-getRadius()-2, getRadius()*2+4, getRadius()*2+4);
				g.setColor(Color.BLACK);
				g.fillOval((int)getCenter().getX()-getRadius()-1, (int)getCenter().getY()-getRadius()-1, getRadius()*2+2, getRadius()*2+2);
			}
			
			Graphics2D g2 = (Graphics2D) g; 
			g2.setColor(this.getColorDrive());
			g2.setStroke(new BasicStroke(3f));
			g2.drawOval((int)getCenter().getX()-getRadius(), (int)getCenter().getY()-getRadius(), getRadius()*2, getRadius()*2);
			
			g.setColor(this.getColorFill());
			g.fillOval((int)getCenter().getX()-getRadius(), (int)getCenter().getY()-getRadius(), getRadius()*2, getRadius()*2);
		}
	}

	public void moveTo(int x, int y) {
		this.center.moveTo(x, y); 
	}

	public void moveBy(int byX, int byY) { 
		this.center.moveBy(byX, byY);
	}

	public int compareTo(Object o) { 
		if(this.getClass() == o.getClass())  
		{	
			Circle c = (Circle)o;
			if(this.area() == c.area()) return 0;
			else if(this.area() > c.area()) return 1;
			else return -1;		
		}else return -1;
	}

	@Override
	public void clone(Shape shape) throws Exception {
		if (shape instanceof Circle) {
			super.clone(shape);
			Circle c = (Circle) shape;
			this.center.clone(c.center);
			this.setRadius(c.radius);
			this.setColorFill(c.colorFill);
		}
	}
}

