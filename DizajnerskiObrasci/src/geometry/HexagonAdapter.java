package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private Hexagon hexagon;
	
	public HexagonAdapter() {
		this.hexagon = new Hexagon(0,0,0);
	}
	
	public HexagonAdapter(Dot center, int r) throws Exception {
		this();
		if(center != null && r>0) 
		{
			this.setX(center.getX());
			this.setY(center.getY());
			this.setR(r);
			this.shape = Shapes.HEXAGON;
			this.selected = false; 
		}else 
		{
			if(center == null) throw new Exception("Invalid Points center.");
			else throw new Exception("Pogresan poluprecnik.");
		}
	}
	
	public HexagonAdapter(Dot center, int r, Color colorDrive, Color colorFill) throws Exception {
		this(center, r);
		this.setColorDrive(colorDrive);
		this.setColorFill(colorFill);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	public void setX(int x) throws Exception {
		if(x < 0) throw new Exception("Koordinate moraju biti pozitivne!");
		else hexagon.setX(x);
	}
	public int getY() {
		return hexagon.getY();
	}
	public void setY(int y) throws Exception {
		if(y < 0) throw new Exception("Koordinate moraju biti pozitivne!");
		else hexagon.setY(y);
	}
	public int getR() {
		return hexagon.getR();
	}
	public void setR(int r) throws Exception {
		if(r < 0) throw new Exception("Poluprecnik mora biti pozitivan!");
		hexagon.setR(r);
	}
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}
	
	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY() + y);
	}

	@Override
	public int compareTo(Object o) {
		if(this.getClass() == o.getClass())  
		{	
			HexagonAdapter h = (HexagonAdapter)o;
			if(this.getR() == h.getR()) return 0;
			else if(this.getR() > h.getR()) return 1;
			else return -1;		
		}else return -1;
	}

	@Override
	public void draw(Graphics g, boolean selected) {
		hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public boolean contains(Dot one) {
		return this.contains(one.getX(), one.getY());
	}

	@Override
	public Color getColorDrive() {
		return hexagon.getBorderColor();
	}
	@Override
	public void setColorDrive(Color color) {
		super.setColorDrive(color);
		hexagon.setBorderColor(color);
	}
	public Color getColorFill() {
		return hexagon.getAreaColor();
	}
	public void setColorFill(Color color) {
		hexagon.setAreaColor(color);
	}
	@Override
	public void clone(Shape shape) throws Exception {
		if (shape instanceof HexagonAdapter) {
			super.clone(shape);
			HexagonAdapter h = (HexagonAdapter) shape;
			this.setX(h.getX());
			this.setY(h.getY());
			this.setR(h.getR());
			this.setColorDrive(h.getColorDrive());
			this.setColorFill(h.getColorFill());
		}
	}
}
