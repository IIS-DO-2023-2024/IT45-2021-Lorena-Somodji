package observer;

import java.util.Observable;
import java.util.Observer;

import mvc.DrawingFrame;

public class UndoRedoObserver implements Observer {
	
	private DrawingFrame frame;
	
	public UndoRedoObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ObservableUndoRedo obs = (ObservableUndoRedo)arg0;
		if(obs.getUndoSize() > 0) {
			frame.getUndoButton().setEnabled(true);
		} else {
			frame.getUndoButton().setEnabled(false);
		}
		
		if(obs.getRedoSize() > 0 ) {
			frame.getRedoButton().setEnabled(true);
		} else {
			frame.getRedoButton().setEnabled(false);
		}
	}
}
