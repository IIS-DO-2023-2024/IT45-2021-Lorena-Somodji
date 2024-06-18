package observer;

import java.util.Observable;

public class ObservableUndoRedo extends Observable {
	private int undoSize;
	private int redoSize;
	
	public int getUndoSize() {
		return this.undoSize;
	}
	
	public void setUndoSize(int undoSize) {
		this.undoSize = undoSize;
		setChanged();
		notifyObservers();
	}
	
	public int getRedoSize() {
		return this.redoSize;
	}
	
	public void setRedoSize(int redoSize) {
		this.redoSize = redoSize;
		setChanged();
		notifyObservers();
	}
}
