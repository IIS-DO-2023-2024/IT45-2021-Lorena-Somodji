package stack;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import geometry.*;

public class Stack extends JFrame{
	
	private DefaultListModel objekti; 
	private JList list; 
	private AddCircle dialog;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Stack window = new Stack();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stack() {
		initialize();
	}

	private void initialize() {
		
		setBounds(100, 100, 300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dialog= new AddCircle();   
		objekti = new DefaultListModel(); 
		

		list = new JList(objekti); 
		list.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		list.setPreferredSize(new Dimension(200,100)); 
		getContentPane().add(list, BorderLayout.CENTER);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel.setPreferredSize(new Dimension(60,60));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		
		panel.setLayout(new GridLayout(1, 5, 20, 20));
		
		
		JButton add = new JButton("DODAJ");  
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					Circle c = dialog.showDialog(null);
					
					
					if(c!=null)  
					{
						objekti.addElement(c); 
						if(objekti.size()>0) 
						{
							for(int i = objekti.size()-1;i>0;i--) 
							{ 
								objekti.setElementAt(objekti.elementAt(i-1), i);
								
							}
							objekti.setElementAt(c, 0);  
						}
					}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Greska",2);
				}	
			}
		});
		
		add.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(add);
		JButton del = new JButton("BRISI");
		del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(objekti.size() > 0) { 
					try { 
						if(dialog.showDialog((Circle) objekti.get(0))!=null) {
							objekti.remove(0);
						}
						
					} catch (Exception e1) {}
				}else JOptionPane.showMessageDialog(null, "Nema krugova!","Informacija",0);
			}
		});
		del.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(del);
	}

}
