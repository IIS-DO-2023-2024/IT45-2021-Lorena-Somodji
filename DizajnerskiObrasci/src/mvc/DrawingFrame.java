package mvc;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame {
	
	public DrawingView view = new DrawingView();
	public DrawingController controller;
	
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseCllicked(e);
			}
		});
		
		
		//JPanel panel = new JPanel;
		getContentPane().add(view, BorderLayout.CENTER);
	}

	
	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	
	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

}
