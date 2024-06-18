package observer;

import java.util.Observable;
import java.util.Observer;

import mvc.DrawingFrame;

public class SelectionObserver implements Observer {
	
	private DrawingFrame frame;
	
	public SelectionObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int selectedAmount = (int)arg1;
		if(selectedAmount > 1) {
			frame.getDelButton().setEnabled(true);
			frame.getEditButton().setEnabled(false);
			frame.getToFrontButton().setEnabled(false);
			frame.getToBackButton().setEnabled(false);
			frame.getBringToFrontButton().setEnabled(false);
			frame.getBringToBackButton().setEnabled(false);
		} else if(selectedAmount == 1) {
			frame.getDelButton().setEnabled(true);
			frame.getEditButton().setEnabled(true);
			frame.getToFrontButton().setEnabled(true);
			frame.getToBackButton().setEnabled(true);
			frame.getBringToFrontButton().setEnabled(true);
			frame.getBringToBackButton().setEnabled(true);
		} else {
			frame.getDelButton().setEnabled(false);
			frame.getEditButton().setEnabled(false);
			frame.getToFrontButton().setEnabled(false);
			frame.getToBackButton().setEnabled(false);
			frame.getBringToFrontButton().setEnabled(false);
			frame.getBringToBackButton().setEnabled(false);
		}
	}

}
