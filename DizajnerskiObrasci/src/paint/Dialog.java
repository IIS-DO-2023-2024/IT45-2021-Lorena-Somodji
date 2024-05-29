package paint;

import geometry.Circle;
import geometry.Donut;
import geometry.Rectangle;
import geometry.Line;
import geometry.Shape;
import geometry.Shapes;
import geometry.Dot;
import geometry.HexagonAdapter;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class Dialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private Boolean result = false;
	private JTextField b;
	private JTextField a;
	
	
	private JLabel naslov;
	private JLabel parameter3;
	private JLabel parameter4;
	
	private Shapes obl;
	private JPanel colorsDrive;
	private JPanel colorsFill;
	
	private JTextField x;
	private JTextField y;
	
	private JPanel inputPanel;
	
	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Dialog() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new BorderLayout(0, 0));
		{	
			naslov = new JLabel("...");  
			naslov.setFont(new Font("Tahoma", Font.PLAIN, 20));
			naslov.setHorizontalAlignment(SwingConstants.CENTER);					
			contentPanel.add(naslov, BorderLayout.NORTH);
		}
		
		{	
			inputPanel = new JPanel();
			contentPanel.add(inputPanel, BorderLayout.CENTER);
			inputPanel.setLayout(new GridLayout(4, 2, 0, 0));
			{
				JLabel parameter1 = new JLabel("X - COORDINATE");
				parameter1.setHorizontalAlignment(SwingConstants.RIGHT);
				parameter1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(parameter1);
			
				x = new JTextField();
				x.setColumns(10);
				x.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),x.getBorder()));
				x.setBackground(SystemColor.menu);
				inputPanel.add(x);
			}
			{
				JLabel parameter2 = new JLabel("Y - COORDINATE");
				parameter2.setHorizontalAlignment(SwingConstants.RIGHT);
				parameter2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(parameter2);
			
				y = new JTextField();
				y.setColumns(10);
				y.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),y.getBorder()));
				y.setBackground(SystemColor.menu);
				inputPanel.add(y);
			}
			{	
					parameter3 = new JLabel("...");
					parameter3.setFont(new Font("Tahoma", Font.PLAIN, 17));
					parameter3.setHorizontalAlignment(SwingConstants.RIGHT);
					inputPanel.add(parameter3);
				
					a = new JTextField();
					a.setColumns(10);
					a.setBackground(SystemColor.menu);
					a.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),a.getBorder()));
					a.setBackground(SystemColor.menu);
					inputPanel.add(a);
				
			}
			{
					parameter4 = new JLabel("...");
					parameter4.setHorizontalAlignment(SwingConstants.RIGHT);
					parameter4.setFont(new Font("Tahoma", Font.PLAIN, 17));
					inputPanel.add(parameter4);
				
					b = new JTextField();
					b.setColumns(10);
					b.setBackground(SystemColor.menu);
					b.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),b.getBorder()));
					b.setBackground(SystemColor.menu);
					inputPanel.add(b);
				
			}
			
		}
		
		{	
			JPanel pan = new JPanel();
			pan.setBackground(Color.WHITE);
			pan.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			pan.setPreferredSize(new Dimension(200,50));
			pan.setLayout(new GridLayout(1,2 ,0, 0));
			contentPanel.add(pan, BorderLayout.SOUTH);
			
			colorsDrive = new JPanel();
			colorsDrive.setBackground(Color.black);
			colorsDrive.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			colorsDrive.setPreferredSize(new Dimension(150,50));
			colorsDrive.setLayout(new BorderLayout(0, 0));
			
			colorsDrive.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Color boja = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK); 
					colorsDrive.setBackground(boja);
				}
			});
			pan.add(colorsDrive, BorderLayout.SOUTH);
			
			JLabel lblNewLabelDrive = new JLabel("CHOOSE DRIVE");
			lblNewLabelDrive.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelDrive.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabelDrive.setForeground(Color.WHITE);
			colorsDrive.add(lblNewLabelDrive, BorderLayout.CENTER);
			
			colorsFill = new JPanel();
			colorsFill.setBackground(Color.GRAY);
			colorsFill.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			colorsFill.setPreferredSize(new Dimension(150,50));
			colorsFill.setLayout(new BorderLayout(0, 0));
			
			colorsFill.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Color boja = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK); 
					colorsFill.setBackground(boja);
				}
			});
			pan.add(colorsFill, BorderLayout.SOUTH);
			
			JLabel lblNewLabelFill = new JLabel("CHOOSE FILL");
			lblNewLabelFill.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabelFill.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabelFill.setForeground(Color.WHITE);
			colorsFill.add(lblNewLabelFill, BorderLayout.CENTER);
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				
				okButton.setBackground(Color.WHITE);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							result = true;
							setVisible(false);
							dispose();
						} catch (NumberFormatException e1) 
						{
							JOptionPane.showMessageDialog(null, "Niste Dobro uneli brojeve!","Greska",2);
						}catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(),"Greska", 2);
						}
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						result = false;
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void Prepare(Shapes ob) 
	{  
		if(ob == Shapes.CIRCLE) 
		{
			naslov.setText("SET CIRCLE PARAMETERS!");
			parameter3.setText("RADIUS LENGHT");
			
			parameter3.setVisible(true);
			parameter4.setVisible(false);
			a.setVisible(true);
			b.setVisible(false);
			colorsFill.setVisible(true);
		}else if(ob == Shapes.RECTANGLE) 
		{	
			naslov.setText("SET RECTANGLE PARAMETERS!");
			parameter3.setText("HEIGHT");
			parameter3.setVisible(true);
			parameter4.setVisible(true);
			parameter4.setText("WIDTH");
			a.setVisible(true);;
			b.setVisible(true);
			colorsFill.setVisible(true);
		}else if (ob == Shapes.DONUT) 
		{	
			naslov.setText("SET DONUT PARAMETERS!");
			parameter3.setText("OUTER RADIUS");
			parameter3.setVisible(true);
			parameter4.setVisible(true);
			parameter4.setText("INNER RADIUS");
			a.setVisible(true);
			b.setVisible(true);
			colorsFill.setVisible(true);
		}else if(ob == Shapes.DOT) 
		{	
			this.naslov.setText("SET DOT PARAMETERS!");
			parameter3.setVisible(false);
			parameter4.setVisible(false);
			a.setVisible(false);
			b.setVisible(false);
			colorsFill.setVisible(false);
		}else if(ob == Shapes.LINE) 
		{	
			naslov.setText("SET LINE PARAMETERS!");
			parameter3.setText("END X COORDINATE");
			parameter3.setVisible(true);;
			parameter4.setVisible(true);
			parameter4.setText("END Y COORDINATE");
			a.setVisible(true);;
			b.setVisible(true);
			colorsFill.setVisible(false);
		}else if(ob == Shapes.HEXAGON) 
		{
			naslov.setText("SET HEXAGON PARAMETERS!");
			parameter3.setText("RADIUS LENGHT");
			
			parameter3.setVisible(true);
			parameter4.setVisible(false);
			a.setVisible(true);
			b.setVisible(false);
			colorsFill.setVisible(true);
		}
	}
	
	public Shape showDialog(Color c, Dot p, Shapes ob)
	{
		this.obl = ob;
		this.colorsDrive.setBackground(c); 
		this.x.setText(p.getX()+""); 
		this.y.setText(p.getY()+"");
		a.setText("");
		b.setText("");
	
		this.Prepare(ob); 
		this.setModal(true);
		this.setVisible(true);
		
		if(result) return this.createShape(ob);
		else return null;
	}
	
	public Shape showDialog(Shape shape)
	{
		if(shape!=null) {
			this.Prepare(shape.getShape());
	
			if (shape instanceof Donut) 
			{
				Donut d = (Donut) shape;
				this.x.setText(d.getCenter().getX()+"");
				this.y.setText(d.getCenter().getY()+"");
				this.a.setText(d.getRadius()+"");
				this.b.setText(d.getInnerRadius()+"");
				this.colorsDrive.setBackground(d.getColorDrive());
				this.colorsFill.setBackground(d.getColorFill());
			}else if(shape instanceof Circle) 
			{
				 Circle c = (Circle) shape;
				 this.x.setText(c.getCenter().getX()+"");
				 this.y.setText(c.getCenter().getY()+"");
				 this.a.setText(c.getRadius()+"");
				 this.colorsDrive.setBackground(c.getColorDrive());
				 this.colorsFill.setBackground(c.getColorFill());
			}else if(shape instanceof Rectangle) 
			{
				Rectangle r = (Rectangle) shape;
				this.x.setText(r.getUpperLeftPoint().getX()+"");
				this.y.setText(r.getUpperLeftPoint().getY()+"");
				this.a.setText(r.getHeight()+"");
				this.b.setText(r.getWidth()+"");
				this.colorsDrive.setBackground(r.getColorDrive());
				this.colorsFill.setBackground(r.getColorFill());
			}else if(shape instanceof Dot) 
			{
				 Dot d = (Dot)shape;
				 this.x.setText(d.getX()+"");
				 this.y.setText(d.getY()+"");
				 this.colorsDrive.setBackground(d.getColorDrive());
			}else if(shape instanceof Line) 
			{
				Line r = (Line) shape;
				this.x.setText(r.getStartPoint().getX()+"");
				this.y.setText(r.getStartPoint().getY()+"");
				this.a.setText(r.getEndPoint().getX()+"");
				this.b.setText(r.getEndPoint().getY()+"");
				this.colorsDrive.setBackground(r.getColorDrive());
			}else if(shape instanceof HexagonAdapter) 
			{
				HexagonAdapter h = (HexagonAdapter) shape;
				this.x.setText(h.getX()+"");
				this.y.setText(h.getY()+"");
				this.a.setText(h.getR()+"");
				this.colorsDrive.setBackground(h.getColorDrive());
				this.colorsFill.setBackground(h.getColorFill());
			}
				
			this.setModal(true);
			this.setVisible(true);
			
			if(result) return this.createShape(shape.getShape()); //this.swapValuesShape(shape); 
			else return null;
		}
		
		return null;
	}	
	
	public Shape createShape(Shapes ob) 
	{
		try {  
			Shape shape;
			
			Dot tacka = new Dot( Integer.parseInt(x.getText().trim()), Integer.parseInt(y.getText().trim()));
			
			if(ob == Shapes.CIRCLE) 
			{
				shape = new Circle(tacka, Integer.parseInt(a.getText().trim()) , colorsDrive.getBackground(), colorsFill.getBackground());
				return shape;
					
			}else if(ob == Shapes.RECTANGLE) 
			{ 
				shape = new Rectangle(tacka, Integer.parseInt(a.getText().trim()) , Integer.parseInt(b.getText().trim()), colorsDrive.getBackground(), colorsFill.getBackground());
				return shape;
				
			}else if (ob == Shapes.DONUT) 
			{
				shape = new Donut(tacka, Integer.parseInt(a.getText().trim()) , Integer.parseInt(b.getText().trim()), colorsDrive.getBackground(), colorsFill.getBackground());
				return shape;
			
			}else if(ob == Shapes.DOT) 
			{
				shape = new Dot(tacka.getX(),tacka.getY(),colorsDrive.getBackground());
				return shape;
			} else if(ob == Shapes.LINE) 
			{
				shape = new Line(tacka,new Dot(Integer.parseInt(a.getText().trim()),Integer.parseInt(b.getText().trim())),colorsDrive.getBackground());
				return shape;
			} else if(ob == Shapes.HEXAGON) 
			{
				shape = new HexagonAdapter(tacka, Integer.parseInt(a.getText().trim()) , colorsDrive.getBackground(), colorsFill.getBackground());
				return shape;
					
			} else return null;
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null; 
		}
	}
	
	public Shape swapValuesShape(Shape shape) 
	{
		try { 
			if (shape instanceof Donut) 
			{
				Donut donut = (Donut)shape;
				donut.moveTo(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
				donut.setRadius(Integer.parseInt(a.getText()));
				donut.setInnerRadius(Integer.parseInt(b.getText()));
				donut.setColorDrive(colorsDrive.getBackground());
				donut.setColorFill(colorsFill.getBackground());
				
				return donut;
			
			} else if(shape instanceof Circle) 
			{ 
				Circle circle = (Circle)shape;
				circle.moveTo(Integer.parseInt(x.getText().trim()), Integer.parseInt(y.getText().trim()));
				circle.setRadius(Integer.parseInt(a.getText().trim()));
				circle.setColorDrive(colorsDrive.getBackground());
				circle.setColorFill(colorsFill.getBackground());
				
				return circle;
					
			}else if(shape instanceof Rectangle) 
			{
				Rectangle rectangle = (Rectangle)shape;
				rectangle.moveTo(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
				rectangle.setHeight(Integer.parseInt(a.getText()));
				rectangle.setWidth(Integer.parseInt(b.getText()));
				rectangle.setColorDrive(colorsDrive.getBackground());
				rectangle.setColorFill(colorsFill.getBackground());
				
				return rectangle;
		
			}else if(shape instanceof Dot) 
			{
				Dot dot = (Dot)shape;
				dot.moveTo(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
				dot.setColorDrive(colorsDrive.getBackground());
				
				return dot;
			} else if(shape instanceof Line) 
			{
				Line line = (Line)shape;
				
				line.setStartPoint(new Dot(Integer.parseInt(x.getText()), Integer.parseInt(y.getText())));
				line.setEndPoint(new Dot(Integer.parseInt(a.getText()),Integer.parseInt(b.getText())));
				line.setColorDrive(colorsDrive.getBackground());
				
				return line;
			} else if(shape instanceof HexagonAdapter) 
			{ 
				HexagonAdapter hexagon = (HexagonAdapter)shape;
				hexagon.moveTo(Integer.parseInt(x.getText().trim()), Integer.parseInt(y.getText().trim()));
				hexagon.setR(Integer.parseInt(a.getText().trim()));
				hexagon.setColorDrive(colorsDrive.getBackground());
				hexagon.setColorFill(colorsFill.getBackground());
				
				return hexagon;
					
			} else return null;
		}catch(Exception e) { 
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null; 
		}
	}

}


