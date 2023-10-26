package paint;

import geometry.Line;
import geometry.Shapes;
import geometry.Circle;
import geometry.Dot;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame{

	private Dot one, two;
	private PnlDrawing drawPanel;
	private JPanel colors; 
	private ButtonGroup btnGroup; 
	private String currentShape;
	private Dialog dialog; 
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainFrame() {
		initialize();
	}
	private void AddObject() 
	{
		try {
			if(currentShape == Shapes.DOT.toString()) 
			{ 
				drawPanel.add(new Dot(one.getX(),one.getY(),colors.getBackground()));
			} else if(currentShape == Shapes.LINE.toString()) 
			{
				if(one != null && two != null) drawPanel.add(new Line(one,two,colors.getBackground()));
			}else if(currentShape == Shapes.RECTANGLE.toString()) 
			{
				drawPanel.add(dialog.showDialog( colors.getBackground(), one, Shapes.RECTANGLE));
			}else if(currentShape == Shapes.CIRCLE.toString()) 
			{
				drawPanel.add(dialog.showDialog(colors.getBackground(), one, Shapes.CIRCLE));
			}else if(currentShape == Shapes.DONUT.toString()) 
			{
				drawPanel.add(dialog.showDialog(colors.getBackground(), one, Shapes.DONUT));
			}
		}catch(Exception e) { JOptionPane.showMessageDialog(null, e, "Greska", 2); }
	}
	
	public String SelektovanoDugme(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
            	currentShape = button.getText(); 
                return button.getText(); 
            } 
        }

        return null;
    }
	
	private void initialize() {

	    setBounds(100, 100, 1022, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		dialog = new Dialog();
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
	    getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		MenuPanel.setBackground(Color.GRAY);
		MenuPanel.setPreferredSize(new Dimension(100,100));
		MainPanel.add(MenuPanel, BorderLayout.NORTH);
		MenuPanel.setLayout(new BorderLayout(0, 0));
		
		colors = new JPanel();
		colors.setBackground(Color.black);
		colors.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		colors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color boja = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);
				colors.setBackground(boja);
			}
		});
		colors.setPreferredSize(new Dimension(200,200));
		MenuPanel.add(colors, BorderLayout.EAST);
		colors.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("CHOOSE COLOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		colors.add(lblNewLabel, BorderLayout.CENTER);

		JPanel shapes = new JPanel();
		shapes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		MenuPanel.add(shapes, BorderLayout.CENTER);
		shapes.setLayout(new GridLayout(1, 5, 15, 10));
		
		btnGroup = new ButtonGroup();
		JRadioButton dot = new JRadioButton(Shapes.DOT.toString());
		dot.setSelected(true); 
		
		JRadioButton line = new JRadioButton(Shapes.LINE.toString());
		JRadioButton rectangle = new JRadioButton(Shapes.RECTANGLE.toString());
		JRadioButton circle = new JRadioButton(Shapes.CIRCLE.toString());
		JRadioButton donut = new JRadioButton(Shapes.DONUT.toString());
		
		btnGroup.add(dot);
		btnGroup.add(line);
		btnGroup.add(rectangle);
		btnGroup.add(circle);
		btnGroup.add(donut);
		
		shapes.add(dot);
		shapes.add(line);
		shapes.add(rectangle);
		shapes.add(circle);
		shapes.add(donut);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.LIGHT_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		MainPanel.add(buttonPane, BorderLayout.SOUTH);
		{
			JButton delButton = new JButton("DELETE");
			delButton.setBackground(Color.WHITE);
			delButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					if(drawPanel.getSelect()){
						int rezult = JOptionPane.showConfirmDialog(
								null,
								"Da li želite da obrišete oblik?",
								"Poruka",
								JOptionPane.YES_NO_OPTION); 
						if(rezult == JOptionPane.YES_OPTION){
							drawPanel.delete();
						}
					} else JOptionPane.showMessageDialog(null, "Nema selektovanog objekata!");
				}
				
				});
				delButton.setActionCommand("");
				buttonPane.add(delButton);
				
				
			}
			{
				 
				JButton editButton = new JButton("EDIT");
				editButton.setBackground(Color.WHITE);
				editButton.setActionCommand("");
				editButton.addMouseListener(new MouseAdapter() {
					@Override 
					public void mouseClicked(MouseEvent e) {
						if(drawPanel.getSelect()) { 
							
						} else JOptionPane.showMessageDialog(null, "Nema selektovanog objekata!");
						
					}
				});
				buttonPane.add(editButton);
			}
		
		drawPanel = new PnlDrawing();
		
		drawPanel.addMouseListener(new MouseAdapter() {
			private String prevSelected; 
			
			@Override
			public void mousePressed(MouseEvent e) {
				SelektovanoDugme(btnGroup); 
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) 
				{
					if(drawPanel.getSelect()) 
					{ 
						drawPanel.setSelect(false);
						return;
					}
					
					if(SelektovanoDugme(btnGroup) == Shapes.LINE.toString() && prevSelected == SelektovanoDugme(btnGroup)) {
						if(two!=null) two = null; 
						else two = one; 
					}else two = null; 
					
					
					Point p = e.getPoint();
					try { 
						one = new Dot(p.x,p.y);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} 
					
					AddObject();
				}else if(e.getButton() == MouseEvent.BUTTON3) 
				{
					Point p = e.getPoint();
					if(p != null) {
						try {
							one = new Dot(p.x,p.y);
							drawPanel.select(one); 
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						
					}
				}
				
				prevSelected = SelektovanoDugme(btnGroup); 
			}
		});
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setBounds(0, 72, 434, 189);
		MainPanel.add(drawPanel,BorderLayout.CENTER);
		
	}
}

