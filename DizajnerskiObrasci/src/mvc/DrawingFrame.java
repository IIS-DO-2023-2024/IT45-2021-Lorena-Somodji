package mvc;

import geometry.Shapes;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawingFrame extends JFrame{

	private DrawingView view = new DrawingView();
	private ButtonGroup btnGroup; 
	private JPanel colors;
	private DrawingController controller;
	private JTextArea textArea;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame window = new DrawingFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public DrawingFrame() {
		initialize();
	}
	
	
	public String selectedButton() {
        for (Enumeration<AbstractButton> buttons = btnGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
            	//currentShape = button.getText(); 
                return button.getText(); 
            } 
        }

        return null;
    }
	
	private void initialize() {

	    setBounds(100, 100, 1022, 464);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
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
		JRadioButton hexagon = new JRadioButton(Shapes.HEXAGON.toString());
		
		btnGroup.add(dot);
		btnGroup.add(line);
		btnGroup.add(rectangle);
		btnGroup.add(circle);
		btnGroup.add(donut);
		btnGroup.add(hexagon);
		
		shapes.add(dot);
		shapes.add(line);
		shapes.add(rectangle);
		shapes.add(circle);
		shapes.add(donut);
		shapes.add(hexagon);
		
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
				
					if(controller.getSelect()){
						int rezult = JOptionPane.showConfirmDialog(
								null,
								"Da li želite da obrišete oblik?",
								"Poruka",
								JOptionPane.YES_NO_OPTION); 
						if(rezult == JOptionPane.YES_OPTION){
							controller.delete();
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
						if(controller.getSelect()) { 
							controller.edit();
						} else JOptionPane.showMessageDialog(null, "Nema selektovanog objekata!");
						
					}
				});
				buttonPane.add(editButton);
			}
			
			JButton undoButton = new JButton("UNDO");
			undoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.undo();
				}
			});
			undoButton.setBackground(Color.WHITE);
			undoButton.setActionCommand("");
			buttonPane.add(undoButton);
			
			JButton redoButton = new JButton("REDO");
			redoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.redo();
				}
			});
			redoButton.setBackground(Color.WHITE);
			redoButton.setActionCommand("");
			buttonPane.add(redoButton);
			
			JButton btnSaveDrawing = new JButton("SAVE");
			btnSaveDrawing.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.saveDrawing();
				}
			});
			btnSaveDrawing.setBackground(Color.WHITE);
			buttonPane.add(btnSaveDrawing);
			
			JButton btnLoadDrawing = new JButton("LOAD");
			btnLoadDrawing.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.loadDrawing();
				}
			});
			btnLoadDrawing.setBackground(Color.WHITE);
			buttonPane.add(btnLoadDrawing);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		view.setBackground(Color.WHITE);
		view.setBounds(0, 72, 434, 189);
		MainPanel.add(view,BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200, 10));
		MainPanel.add(scrollPane, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		panel.add(textArea, BorderLayout.CENTER);
		
		JLabel lblLog = new JLabel("LOG");
		panel.add(lblLog, BorderLayout.NORTH);
		
	}


	public DrawingView getView() {
		return this.view;
	}


	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public Color getColor() {
		return colors.getBackground();
	}
	public JTextArea getTextArea() {
		return textArea;
	}
}

