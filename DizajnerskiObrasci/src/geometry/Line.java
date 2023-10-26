package geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape {
	
	private Dot startPoint;
	private Dot endPoint;

	public Line() {
		startPoint = new Dot();
		endPoint = new Dot();		
	}
	
	public Line(Dot one, Dot two, Color colorDrive) throws Exception 
	{
		if(one != null && two != null) 
		{
			this.startPoint = one;
			this.endPoint = two;
			this.colorDrive=colorDrive;
			this.selected=false;
			this.shape = Shapes.LINE;
		}else 
		{
			if(one==null && two==null) throw new Exception("Invalid Points obe tacke!");
			else if(one == null) throw new Exception("Invalid Points prve tacke!");
			else throw new Exception("Invalid Points druge tacke!");
		}
	}

	public double length() {
		return Math.abs(Math.sqrt(Math.pow((getStartPoint().getX())-getEndPoint().getX(), 2)+Math.pow((getStartPoint().getY())-getEndPoint().getY(), 2)));
	}

	public Dot getStartPoint() {
		return this.startPoint;
	}

	public void setStartPoint(Dot startPoint) throws Exception 
	{
		this.startPoint = new Dot(startPoint.getX(), startPoint.getY());	
	}

	public Dot getEndPoint() 
	{
		return this.endPoint;
	}

	public void setEndPoint(Dot endPoint) throws Exception 
	{
		this.endPoint = new Dot(endPoint.getX(), endPoint.getY());
	}

	@Override
	public String toString() {
		return this.startPoint + "-->" + this.endPoint;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line l = (Line)obj;
			if(this.getStartPoint().equals(l.getStartPoint()) && this.getEndPoint().equals(l.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		double d = this.getStartPoint().distance(x, y) + this.getEndPoint().distance(x, y);
		return d - this.length() <= 2;
	}

	@Override
	public void draw(Graphics g, boolean selected) {
		if(g!=null) {
			if(selected) 
			{
				g.setColor(Color.BLACK);
				g.drawLine(getStartPoint().getX()+2, getStartPoint().getY()+2, getEndPoint().getX()+2, getEndPoint().getY()+2);
				g.setColor(Color.CYAN);
				g.drawLine(getStartPoint().getX()+1, getStartPoint().getY()+1, getEndPoint().getX()+1, getEndPoint().getY()+1);
				g.setColor(Color.CYAN);
				g.drawLine(getStartPoint().getX()-1, getStartPoint().getY()-1, getEndPoint().getX()-1, getEndPoint().getY()-1);
				g.setColor(Color.BLACK);
				g.drawLine(getStartPoint().getX()-2, getStartPoint().getY()-2, getEndPoint().getX()-2, getEndPoint().getY()-2);
			}
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(this.getColorDrive());
			g2.setStroke(new BasicStroke(1.0f));
			g2.setColor(this.colorDrive);
			g2.drawLine(getStartPoint().getX(), getStartPoint().getY(), getEndPoint().getX(), getEndPoint().getY());
		}
	}

	public void moveTo(int x, int y) {
		((Moveable) this.startPoint).moveTo(x, y);
		((Moveable) this.endPoint).moveTo(x, y);
	}

	public void moveBy(int byX, int byY) {
		((Moveable) this.startPoint).moveBy(byX, byY);
		((Moveable) this.endPoint).moveBy(byX, byY);
	}

	public int compareTo(Object o) {
		if(o instanceof Line) {
			return (int)(this.length()-((Line)o).length());
		}
		return 0;
	}

	@Override
	public boolean contains(Dot one) {
		if(this.getStartPoint().distance(one.getX(), one.getY())+this.getEndPoint().distance(one.getX(), one.getY()) - this.length() < 0.05f && this.getStartPoint().distance(one.getX(), one.getY())+this.getEndPoint().distance(one.getX(), one.getY()) - this.length() > -0.05f) return true;
		else return false;
	}
}

