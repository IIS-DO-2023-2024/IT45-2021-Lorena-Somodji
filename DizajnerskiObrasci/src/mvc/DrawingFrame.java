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
	private JPanel innerColor;
	private DrawingController controller;
	private JTextArea textArea;
	private JButton toFrontButton;
	private JButton bringToBackButton;
	private JButton bringToFrontButton;
	private JButton undoButton;
	private JButton delButton;
	private JButton redoButton;
	private JButton editButton;
	private JButton toBackButton;
	
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

	    setBounds(100, 100, 1114, 464);
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
				Color boja = JColorChooser.showDialog(null, "Izaberi boju", colors.getBackground());
				if(boja != null)
					colors.setBackground(boja);
			}
		});
		colors.setPreferredSize(new Dimension(150, 200));
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
		
		innerColor = new JPanel();
		innerColor.setBackground(Color.GRAY);
		shapes.add(innerColor);
		innerColor.setLayout(new BorderLayout(0, 0));
		innerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color boja = JColorChooser.showDialog(null, "Izaberi boju", innerColor.getBackground());
				if(boja != null)
					innerColor.setBackground(boja);
			}
		});
		
		JLabel lblChooseInnerColor = new JLabel("INNER COLOR");
		lblChooseInnerColor.setFont(new Font("Tahoma", Font.BOLD, 14));
		innerColor.add(lblChooseInnerColor, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.LIGHT_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		MainPanel.add(buttonPane, BorderLayout.SOUTH);
		{
			delButton = new JButton("DELETE");
			delButton.setEnabled(false);
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
				editButton = new JButton("EDIT");
				editButton.setEnabled(false);
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
			
			undoButton = new JButton("UNDO");
			undoButton.setEnabled(false);
			undoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.undo();
				}
			});
			undoButton.setBackground(Color.WHITE);
			undoButton.setActionCommand("");
			buttonPane.add(undoButton);
			
			redoButton = new JButton("REDO");
			redoButton.setEnabled(false);
			redoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.redo();
				}
			});
			redoButton.setBackground(Color.WHITE);
			redoButton.setActionCommand("");
			buttonPane.add(redoButton);
			
			JButton saveDrawingButton = new JButton("SAVE");
			saveDrawingButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.saveDrawing();
				}
			});
			
			toFrontButton = new JButton("TO FRONT");
			toFrontButton.setEnabled(false);
			toFrontButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.toFront();
				}
			});
			toFrontButton.setBackground(Color.WHITE);
			buttonPane.add(toFrontButton);
			
			toBackButton = new JButton("TO BACK");
			toBackButton.setEnabled(false);
			toBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.toBack();
				}
			});
			toBackButton.setBackground(Color.WHITE);
			buttonPane.add(toBackButton);
			
			bringToFrontButton = new JButton("BRING TO FRONT");
			bringToFrontButton.setEnabled(false);
			bringToFrontButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.bringToFront();
				}
			});
			bringToFrontButton.setBackground(Color.WHITE);
			buttonPane.add(bringToFrontButton);
			
			bringToBackButton = new JButton("BRING TO BACK");
			bringToBackButton.setEnabled(false);
			bringToBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.bringToBack();
				}
			});
			bringToBackButton.setBackground(Color.WHITE);
			buttonPane.add(bringToBackButton);
			saveDrawingButton.setBackground(Color.WHITE);
			buttonPane.add(saveDrawingButton);
			
			JButton loadDrawingButton = new JButton("LOAD");
			loadDrawingButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.loadDrawing();
				}
			});
			loadDrawingButton.setBackground(Color.WHITE);
			buttonPane.add(loadDrawingButton);
			
			JButton saveLogButton = new JButton("SAVE LOG");
			saveLogButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.saveLog();
				}
			});
			saveLogButton.setBackground(Color.WHITE);
			buttonPane.add(saveLogButton);
			
			JButton loadLogButton = new JButton("LOAD LOG");
			loadLogButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.loadLog();
				}
			});
			loadLogButton.setBackground(Color.WHITE);
			buttonPane.add(loadLogButton);
		
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
	public Color getInnerColor() {
		return innerColor.getBackground();
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JButton getToFrontButton() {
		return toFrontButton;
	}
	public JButton getBringToBackButton() {
		return bringToBackButton;
	}
	public JButton getBringToFrontButton() {
		return bringToFrontButton;
	}
	public JButton getUndoButton() {
		return undoButton;
	}
	public JButton getDelButton() {
		return delButton;
	}
	public JButton getRedoButton() {
		return redoButton;
	}
	public JButton getEditButton() {
		return editButton;
	}
	public JButton getToBackButton() {
		return toBackButton;
	}
}

