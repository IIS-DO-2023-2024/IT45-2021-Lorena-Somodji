package stackwindow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import geometry.Circle;
import geometry.Dot;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddCircle1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField y;
	private JTextField x;
	private JTextField r;
	private Boolean result = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCircle1 dialog = new AddCircle1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddCircle1() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel1 = new JLabel("STVARANJE CIRCLE OBJEKTA");
			lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel1, BorderLayout.NORTH);
		}
		{
			JPanel inputPanel = new JPanel();
			contentPanel.add(inputPanel, BorderLayout.CENTER);
			inputPanel.setLayout(new GridLayout(3, 2, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("X KOORDINATA");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(lblNewLabel_1);
			}
			{
				x = new JTextField();
				inputPanel.add(x);
				x.setColumns(10);
				x.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),x.getBorder()));
				x.setBackground(SystemColor.menu);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Y KOORDINATA");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(lblNewLabel_2);
			}
			{
				y = new JTextField();
				inputPanel.add(y);
				y.setColumns(10);
				y.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),y.getBorder()));
				y.setBackground(SystemColor.menu);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("RADIUS");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				inputPanel.add(lblNewLabel_1);
			}
			{
				r = new JTextField();
				inputPanel.add(r);
				r.setColumns(10);
				r.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),r.getBorder()));
				r.setBackground(SystemColor.menu);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							
							Circle c = new Circle(new Dot(Integer.parseInt(x.getText()), Integer.parseInt(y.getText())),Integer.parseInt(r.getText()));
							result = true; 
							setVisible(false); 
							dispose();
						} catch (NumberFormatException e1) 
						{
							JOptionPane.showMessageDialog(null, "Niste Dobro uneli brojeve!","Greska",2);
						}catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Brojevi koje ste uneli nisu dobri!","Greska",2);

						}
					}
				});
			
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public Circle showDialogx(Circle o) throws Exception 
	{
		if(o != null) 
		{
			x.setText(o.getCenter().getX()+"");
			x.setEnabled(false);
			y.setText(o.getCenter().getY()+"");
			y.setEnabled(false);
			r.setText(o.getRadius()+"");
			r.setEnabled(false);
		}else 
		{
			x.setText("");
			x.setEnabled(true);
			y.setText("");
			y.setEnabled(true);
			r.setText("");
			r.setEnabled(true);
		}
		
		this.setModal(true);
		this.setVisible(true);
		
		if(result) 
		{
			try {
				Circle c = new Circle(new Dot(Integer.parseInt(x.getText()), Integer.parseInt(y.getText())),Integer.parseInt(r.getText()));
				return c;
			} catch (NumberFormatException e1) 
			{
				throw e1;
			}catch (Exception e) {
				throw e;
			}
		}else return null;
	}


}
