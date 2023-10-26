package paint1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Dot;
import geometry.Line;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Shapes;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dialog1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField x;
	private JTextField y;
	private JTextField a;
	private JTextField b;
	private Boolean result = false;
	private JPanel colorsDrive;
	private JPanel colorsFill;
	private Shapes obl;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialog1 dialog = new Dialog1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog1() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel naslov = new JLabel("....");
			naslov.setHorizontalAlignment(SwingConstants.CENTER);
			naslov.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(naslov, BorderLayout.NORTH);
		}
		{
			JPanel inputPanel = new JPanel();
			contentPanel.add(inputPanel, BorderLayout.CENTER);
			inputPanel.setLayout(new GridLayout(4, 2, 0, 0));
			{
				JLabel parametar1 = new JLabel("X KOORDINATA");
				parametar1.setFont(new Font("Tahoma", Font.PLAIN, 19));
				parametar1.setHorizontalAlignment(SwingConstants.CENTER);
				inputPanel.add(parametar1);
			}
			{
				x = new JTextField();
				inputPanel.add(x);
				x.setColumns(10);
				x.setBackground(SystemColor.menu);
				x.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),x.getBorder()));
			}
			{
				JLabel parametar2 = new JLabel("Y KOORDINATA");
				parametar2.setHorizontalAlignment(SwingConstants.CENTER);
				parametar2.setFont(new Font("Tahoma", Font.PLAIN, 19));
				inputPanel.add(parametar2);
			}
			{
				y = new JTextField();
				inputPanel.add(y);
				y.setColumns(10);
				y.setBackground(SystemColor.menu);
				y.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),y.getBorder()));
			}
			{
				JLabel parametar3 = new JLabel("....");
				parametar3.setBackground(new Color(240, 240, 240));
				parametar3.setHorizontalAlignment(SwingConstants.CENTER);
				parametar3.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(parametar3);
			}
			{
				a = new JTextField();
				inputPanel.add(a);
				a.setColumns(10);
				a.setBackground(SystemColor.menu);
				a.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),a.getBorder()));
			}
			{
				JLabel parametar4 = new JLabel("....");
				parametar4.setHorizontalAlignment(SwingConstants.CENTER);
				parametar4.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(parametar4);
			}
			{
				b = new JTextField();
				inputPanel.add(b);
				b.setColumns(10);
				b.setBackground(SystemColor.menu);
				b.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),b.getBorder()));
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(1, 2, 0, 0));
			{
				JButton colorsDrive = new JButton("CHOOSE DRIVE");
				colorsDrive.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Color boja = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK); 
						colorsDrive.setBackground(boja);
					}
				});
				
				colorsDrive.setForeground(Color.WHITE);
				colorsDrive.setBackground(Color.BLACK);
				colorsDrive.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				panel.add(colorsDrive);
			}
			{
				JButton colorsFill = new JButton("CHOOSE FILL");
				colorsFill.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Color boja = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK); 
						colorsFill.setBackground(boja);
					}
				});
				colorsFill.setFont(new Font("Tahoma", Font.BOLD, 12));
				colorsFill.setForeground(Color.WHITE);
				colorsFill.setBackground(Color.GRAY);
				panel.add(colorsFill);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(1, 2, 30, 30));
			{
				JButton okButton = new JButton("OK");
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
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
}