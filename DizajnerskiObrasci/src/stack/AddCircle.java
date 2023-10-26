package stack;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import geometry.*;


public class AddCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Boolean result = false;
	
	private JTextField r; 
	private JTextField y; 
	private JTextField x; 
	
	
	public static void main(String[] args) {
		try {
			AddCircle dialog = new AddCircle(); 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AddCircle() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			
			JLabel lblNewLabel = new JLabel("STVARANJE CIRCLE OBJEKTA");  
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);					
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			
			JPanel inputPanel = new JPanel();
			contentPanel.add(inputPanel, BorderLayout.CENTER);
			
			 
			inputPanel.setLayout(new GridLayout(3, 2, 0, 0));
			{
				
					JLabel lblNewLabel_1 = new JLabel("X KOORDINATA");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
					inputPanel.add(lblNewLabel_1);
				
				    
					x = new JTextField(); 
					x.setColumns(10); 
					x.setBackground(SystemColor.menu);
					x.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),x.getBorder()));
					inputPanel.add(x);			
			}
			
			{
					JLabel lblNewLabel_1 = new JLabel("Y KOORDINATA");
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					inputPanel.add(lblNewLabel_1);
				
					y = new JTextField();
					y.setColumns(10);
					y.setBackground(SystemColor.menu);
					y.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),y.getBorder()));
					y.setBackground(SystemColor.menu);
					inputPanel.add(y);
				
			}
			
			{
				
					JLabel lblNewLabel_1 = new JLabel("POLUPRECNIK");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
					inputPanel.add(lblNewLabel_1);

					r = new JTextField();
					r.setBackground(SystemColor.menu);
					r.setBackground(SystemColor.menu);
					r.setBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),r.getBorder()));
					inputPanel.add(r);
					r.setColumns(10);	
			}
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
							JOptionPane.showMessageDialog(null, "Brojevi koje ste uneli nisu dobri!","Greska",2);
						}
					}
				});
				okButton.setActionCommand("OK"); 
				buttonPane.add(okButton); 
				getRootPane().setDefaultButton(okButton);  
			}
	
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
	
	public Circle showDialog(Circle o) throws Exception 
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
