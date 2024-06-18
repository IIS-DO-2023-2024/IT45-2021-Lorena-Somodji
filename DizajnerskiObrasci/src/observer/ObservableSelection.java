package observer;

import java.util.Observable;

public class ObservableSelection extends Observable {
	private int selectedAmount = 0;
	
	public int getSelectedAmount() {
		return this.selectedAmount;
	}
	
	public void setSelectedAmount(int selectedAmount) {
		this.selectedAmount = selectedAmount;
		setChanged();
		notifyObservers(selectedAmount);
	}
}
