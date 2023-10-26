package stackwindow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import geometry.Circle;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stack1 extends JFrame {

	private JPanel contentPane;
	private AddCircle1 dijalog;
	private DefaultListModel objekti;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack1 frame = new Stack1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Stack1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		dijalog=new AddCircle1();
		objekti = new DefaultListModel();
		
		
		JList list = new JList(objekti);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		contentPane.add(list, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 2, 20, 20));
		
		JButton add = new JButton("DODAJ");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					Circle c = dijalog.showDialogx(null);
					
					
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
		add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(add);
		
		JButton del = new JButton("BRISI");
		del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(list.getSelectedIndex()!=-1) { 
					try {  
						if(dijalog.showDialogx((Circle) objekti.get(list.getSelectedIndex()))!=null) {
							objekti.remove(list.getSelectedIndex()); 
						
						}
						
					} catch (Exception e1) {}
				}else JOptionPane.showMessageDialog(null, "Selektuj neki element","Informacija",1);
			
			}
		});
		del.setFont(new Font("Tahoma", Font.PLAIN, 17));
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(del);
	}

}
