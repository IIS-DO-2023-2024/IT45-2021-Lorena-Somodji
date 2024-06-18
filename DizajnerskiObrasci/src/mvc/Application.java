package mvc;

import javax.swing.JFrame;

import observer.SelectionObserver;
import observer.UndoRedoObserver;
import strategy.DrawingFileImpl;
import strategy.LogFileImpl;

public class Application {

	public static void main(String[] args) {
	System.out.println("Dobrodosli.");

	DrawingModel model = new DrawingModel();
	DrawingFrame frame = new DrawingFrame();
	frame.getView().setModel(model);
	DrawingController controller = new DrawingController (model, frame);
	frame.setController(controller);
	
	DrawingFileImpl drawingFileStrategy = new DrawingFileImpl();
	controller.setDrawingFileStrategy(drawingFileStrategy);
	LogFileImpl logFileStrategy = new LogFileImpl();
	controller.setLogFileStrategy(logFileStrategy);
	
	SelectionObserver selectionObserver = new SelectionObserver(frame);
	controller.addSelectionObserver(selectionObserver);
	UndoRedoObserver undoRedoObserver = new UndoRedoObserver(frame);
	controller.addUndoRedoObserver(undoRedoObserver);
	
	//frame.setSize(600,400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	}

}