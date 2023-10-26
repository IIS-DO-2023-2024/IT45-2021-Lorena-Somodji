package geometry;

import java.awt.*;
import java.awt.Point;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.border.LineBorder;

public class TestFrame extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame window = new TestFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TestFrame() {
		initialize();
	}
	
	private void initialize() {
		try {
			setBounds(100, 100, 300, 500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout(0, 0));
		
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			panel.setPreferredSize(new Dimension(60,60));
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			
			
			PnlDrawing drawPanel = new PnlDrawing();
			panel.add(drawPanel,BorderLayout.CENTER);
		
			Circle circle = new Circle(new geometry.Point(40,40), 10, Color.RED);
			circle.setSelected(true);
			drawPanel.add(circle);
			
			Dot dot = new Dot(100,50, Color.BLACK);
			drawPanel.add(dot);
			
			Rectangle rectangle = new Rectangle(new Point(100,200), 100, 50, Color.BLUE);
			drawPanel.add(rectangle);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
