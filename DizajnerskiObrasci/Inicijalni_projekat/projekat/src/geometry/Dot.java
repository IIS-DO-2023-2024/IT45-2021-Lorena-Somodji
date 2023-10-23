package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Dot extends Shape {

	private int x;
	private int y;

	public Dot() {

	}

	public Dot(int x, int y) throws Exception 
	{
		if(x>=0 && y>=0) {
			this.x = x;
			this.y = y;
			this.shape = Shapes.DOT;
		}else {
			throw new Exception("Koordinate moraju biti pozoitivne!");
		}
	}
	
	public Dot(int x, int y, Color colorDrive) throws Exception    
	{
		this(x,y);
		this.selected = false;
		this.colorDrive=colorDrive;
	}
	
	public int getX() {
		return this.x;
	}

	public void setX(int x_coord) {
		if (x_coord > 0) {
			x = x_coord;
		}else {
			System.out.println("Vrednost mora da bude pozitivna");
		}
	}

	public static void point_static_method() {
		System.out.println("Ovo je staticka metoda");
	}

	public int getY() {
		return y;
	}

	public void setY(int y_coord) {
		y = y_coord;
	}

	public double distance(int x2, int y2) {
		double dx = x2 - this.x;
		double dy = y2 - this.y;
		double d = Math.sqrt(dx*dx + dy*dy);
		
		return d;
	}

	
	@Override
	public String toString() {
		return "("+this.x+","+this.y+")";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Dot) {
			Dot p = (Dot)obj;
			if(this.x == p.x && this.y == p.y) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public static void staticMethod() {
		System.out.println("Poziv staticke metode");
	}

	@Override
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	public void draw(Graphics g) {
		g.drawLine(this.x, this.y, this.x, this.y);
		if(this.selected) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
			g.setColor(Color.BLACK);
		}
	}

	public void moveBy(int x, int y) {
		this.x += x;
		this.y = this.y + y;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Object obj) {
		if(obj instanceof Point) {
			return (int) (this.distance(0, 0) - ((Point)obj).distance(0, 0));
		}else {
			return 0;
		}
	}

	@Override
	public void draw(Graphics g, boolean selected) {
		int r = 4;
		Point center = new Point(this.getX(),this.getY());
		
		if(g!=null) {
			if(selected) 
			{
				g.setColor(Color.BLACK);
				g.fillOval(center.x-r-3, center.y-r-3, r*2+6, r*2+6);
				g.setColor(Color.CYAN);
				g.fillOval(center.x-r-2, center.y-r-2, r*2+4, r*2+4);
				g.setColor(Color.BLACK);
				g.fillOval(center.x-r-1, center.y-r-1, r*2+2, r*2+2);
			}
			g.setColor(this.colorDrive);
			g.fillOval(center.x-r, center.y-r, r*2, r*2);
		}
	}

	@Override
	public boolean contains(Dot one)  
	{
		return ( this.distance(one.getX(), one.getY()) <= 4 );
	}

}


