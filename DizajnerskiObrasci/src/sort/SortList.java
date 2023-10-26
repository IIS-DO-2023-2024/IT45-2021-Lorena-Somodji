package sort;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

import geometry.Circle;

public class SortList extends JFrame{

	private ModifiedListModel objekti;
	//private DefaultListModel objekti;
	private JList list;
	private AddCircleDialog dijalog;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortList window = new SortList();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SortList() {
		initialize();
	}


	private void initialize() {
		
		setBounds(100, 100, 300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dijalog = new AddCircleDialog();
		objekti = new ModifiedListModel();
		
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
					Circle c = dijalog.showDialog(null);
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
		add.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(add);
		
		JButton del = new JButton("BRISI");
		del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.getSelectedIndex()!=-1) {
					try {
						if(dijalog.showDialog((Circle) objekti.get(list.getSelectedIndex()))!=null) {
							objekti.remove(list.getSelectedIndex());
						}
						
					} catch (Exception e1) {}
				}else JOptionPane.showMessageDialog(null, "Selektuj neki element","Informacija",1);
			}
		});
		del.setFont(new Font("Arial", Font.PLAIN, 17));
		panel.add(del);
	}

}

//









