package sortwindow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import geometry.Circle;
import sort.ModifiedListModel;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sort1 extends JFrame {

	private JPanel contentPane;
	private AddCircleDialog1 dijalog;
	private ModifiedListModel objekti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort1 frame = new Sort1();
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
	public Sort1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dijalog = new AddCircleDialog1();
		objekti = new ModifiedListModel();

		
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
					Circle c = dijalog.showDialogy(null);
					if(c!=null) 
					{
						objekti.addElement(c);
						objekti.Sort(1);
					}
				}catch (Exception e1) { 
					JOptionPane.showMessageDialog(null, e1.toString(),"Greska",2);
				}
			}
		});
		add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(add);
		
		JButton del = new JButton("BRISI");
		del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.getSelectedIndex()!=-1) {
					try {
						if(dijalog.showDialogy((Circle) objekti.get(list.getSelectedIndex()))!=null) {
							objekti.remove(list.getSelectedIndex());
						}
						
					} catch (Exception e1) {}
				}else JOptionPane.showMessageDialog(null, "Selektuj neki element","Informacija",1);
			}
		});
		del.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(del);
	}

}
