package geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private int inner_radius; 
	
	public Donut() {
		super();
	}
	
	public Donut(Dot center, int outer_radius, int inner_radius, Color colorDrive, Color colorFill) throws Exception {
		super(center, outer_radius, colorDrive, colorFill);
		if(outer_radius != inner_radius && outer_radius > inner_radius) 
		{
			this.inner_radius = inner_radius; 
			this.shape = Shapes.DONUT;
		}
		else {
			if(outer_radius == inner_radius)  throw new Exception("Poluprecnici moraju biti razliciti");
			if(outer_radius <= inner_radius)  throw new Exception("Spoljasnji poluprecnik mora biti veci od unutrasnjeg!");
		}
	}
	
	@Override
	public void draw(Graphics g, boolean selected) {
		if(g!=null)
		{
			if(selected) 
			{
				
				g.drawOval(getCenter().getX()-getRadius()-3, getCenter().getY()-getRadius()-3, getRadius()*2+6, getRadius()*2+6);
				g.setColor(Color.pink);
				g.setColor(Color.BLACK);
				g.drawOval(getCenter().getX()-getRadius()-3, getCenter().getY()-getRadius()-3, getRadius()*2+6, getRadius()*2+6);
				g.setColor(Color.CYAN);
				g.drawOval(getCenter().getX()-getRadius()-2, getCenter().getY()-getRadius()-2, getRadius()*2+4, getRadius()*2+4);
				g.setColor(Color.BLACK);
				g.drawOval(getCenter().getX()-getRadius()-1, getCenter().getY()-getRadius()-1, getRadius()*2+2, getRadius()*2+2);
				g.setColor(Color.CYAN);
				
			
			 }
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D outerElipse = new Ellipse2D.Double(getCenter().getX() - getRadius(), 
					getCenter().getY() - getRadius(), 
					2 * getRadius(), 
					2 * getRadius());
			Ellipse2D innerEllipse = new Ellipse2D.Double(getCenter().getX() - inner_radius, 
					getCenter().getY() - inner_radius, 
					2 * inner_radius,
					2 * inner_radius);
			Area outerArea = new Area(outerElipse);
	        Area innerArea = new Area(innerEllipse);
	        outerArea.subtract(innerArea);
			g2.setColor(this.getColorFill());
			g2.fill(outerArea);
			
			g2.setColor(this.getColorDrive());
			g2.setStroke(new BasicStroke(3f));
			g2.drawOval(getCenter().getX()-getInnerRadius(), getCenter().getY()-getInnerRadius(), getInnerRadius()*2, getInnerRadius()*2);
			g2.drawOval(getCenter().getX()-getRadius(), getCenter().getY()-getRadius(), getRadius()*2, getRadius()*2);
		}
	}
	
	@Override
	public boolean contains(int x, int y) { 
		if ( this.getCenter().distance(x, y) >= this.getInnerRadius() && this.getCenter().distance(x, y) <= this.getRadius() ) return true;
		else return false;
	}

	@Override
	public boolean contains(Dot point) {
		if ( this.getCenter().distance(point.getX(), point.getY()) >= this.getInnerRadius() && this.getCenter().distance(point.getX(), point.getY()) <= this.getRadius() ) return true;
		else return false;
	}

	@Override
	public double area() {
		double p = super.area() - Math.pow(this.getInnerRadius(), 2)*Math.PI;
		return Math.round(p*100.0)/100.0;
	}
	
	public int getInnerRadius() 
	{
		return this.inner_radius;
	}
	
	public void setInnerRadius(int innerRadius) throws Exception
	{
		if(this.getRadius() != innerRadius && this.getRadius() > innerRadius && innerRadius>0  ) 
		{
			this.inner_radius = innerRadius; 
		}
		else
		{
			if(innerRadius <=0 ) throw new Exception ("Unutrasnji poluprecnik mora biti pozitivan!");
			else if (innerRadius >= super.getRadius()) throw new Exception ("Spoljasnji poluprecnik mora biti veci od unutrasnjeg!");
		}
	}
	
	@Override
	public void clone(Shape shape) throws Exception {
		if (shape instanceof Donut) {
			super.clone(shape);
			this.setInnerRadius(((Donut)shape).inner_radius);
		}
	}

	@Override
	public String toString() {
		return "Donut [center=" + getCenter() + ", radius=" + getRadius() + ", inner_radius=" + inner_radius
				+ ", colorDrive=" + getColorDrive() + ", colorFill=" + getColorFill() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			return super.equals(obj) && this.inner_radius == ((Donut)obj).inner_radius;
		}
		return false;
	}
}

