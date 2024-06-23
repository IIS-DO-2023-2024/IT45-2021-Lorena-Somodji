package mvc;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectShapeCmd;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateShapeCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.Dot;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Shapes;
import observer.ObservableSelection;
import observer.ObservableUndoRedo;
import paint.Dialog;
import strategy.DrawingFileStrategy;
import strategy.LogFileStrategy;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private Dialog dialog = new Dialog(); 
	
	private String currentShape = Shapes.DOT.toString();
	private String prevSelected;
	private Dot one, two;
	
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	private DrawingFileStrategy drawingFileStrategy;
	private LogFileStrategy logFileStrategy;
	
	private ObservableSelection observableSelection = new ObservableSelection();
	private ObservableUndoRedo observableUndoRedo = new ObservableUndoRedo();
	
	public DrawingController (DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
	}
	
	public void setDrawingFileStrategy(DrawingFileStrategy drawingFileStrategy) {
		this.drawingFileStrategy = drawingFileStrategy;
	}
	public void setLogFileStrategy(LogFileStrategy logFileStrategy) {
		this.logFileStrategy = logFileStrategy;
	}
	public void addSelectionObserver(Observer observer) {
		observableSelection.addObserver(observer);
	}
	public void addUndoRedoObserver(Observer observer) {
		observableUndoRedo.addObserver(observer);
	}
	
	public boolean getSelect()
	{
		return !this.selectedShapes.isEmpty();
	}
	public int getSelectedAmount() {
		int num = 0;
		for(Shape s : model.getShapes()) {
			if(s.isSelected())
				num++;
		}
		return num;
	}
	
	private void updateObservers() {
		observableSelection.setSelectedAmount(getSelectedAmount());
		observableUndoRedo.setUndoSize(undoStack.size());
		observableUndoRedo.setRedoSize(redoStack.size());
	}
	
	private void execute(Command cmd) {
		cmd.execute();
		undoStack.push(cmd);
		redoStack.clear();
		frame.getTextArea().append(cmd.toString() + '\n');
		updateObservers();
		frame.repaint();
	}
	
	public void undo() {
		if(undoStack.empty()) {
			JOptionPane.showMessageDialog(null, "Nothing to undo!");
			return;
		}
		Command cmd = undoStack.pop();
		cmd.unexecute();
		redoStack.push(cmd);
		frame.getTextArea().append("Undo - " + cmd + '\n');
		updateObservers();
		frame.repaint();
	}
	public void redo() {
		if(redoStack.empty()) {
			JOptionPane.showMessageDialog(null, "Nothing to redo!");
			return;
		}
		Command cmd = redoStack.pop();
		cmd.execute();
		undoStack.push(cmd);
		frame.getTextArea().append("Redo - " + cmd + '\n');
		updateObservers();
		frame.repaint();
	}
	
	private void select(Dot one) {
		for(int i = model.size()-1; i>=0; i--)
		{
			Shape shape = model.get(i);
			if(shape.contains(one)) 
			{
				select(shape);
				return;
			}
		}
		this.deselect();
	}
	
	private void select(Shape shape) {
		if(!shape.isSelected()) {
			execute(new SelectShapeCmd(shape));
			selectedShapes.add(shape);
		} else {
			execute(new DeselectShapeCmd(shape));
			selectedShapes.remove(shape);
		}
	}
	
	private void deselect() {
		for(Shape s : model.getShapes()) {
			if(s.isSelected()) execute(new DeselectShapeCmd(s));
		}
		this.selectedShapes.clear();
	}
	
	private void AddObject() 
	{
		try {
			Shape shape = null;
			if(currentShape == Shapes.DOT.toString()) 
			{ 
				shape = new Dot(one.getX(),one.getY(),frame.getColor());
			} else if(currentShape == Shapes.LINE.toString()) 
			{
				if(one != null && two != null) shape = new Line(one,two,frame.getColor());
			}else if(currentShape == Shapes.RECTANGLE.toString()) 
			{
				shape = dialog.showDialog( frame.getColor(), frame.getInnerColor(), one, Shapes.RECTANGLE);
			}else if(currentShape == Shapes.CIRCLE.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), frame.getInnerColor(), one, Shapes.CIRCLE);
			}else if(currentShape == Shapes.DONUT.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), frame.getInnerColor(), one, Shapes.DONUT);
			}else if(currentShape == Shapes.HEXAGON.toString()) 
			{
				shape = dialog.showDialog(frame.getColor(), frame.getInnerColor(), one, Shapes.HEXAGON);
			}
			
			if(shape != null) {
				execute(new AddShapeCmd(shape, model));
			}
		}catch(Exception e) { JOptionPane.showMessageDialog(null, e, "Greska", 2); }
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
		{
			this.prevSelected = this.currentShape;
			this.currentShape = frame.selectedButton();
			
			this.deselect();
			
			if(currentShape == Shapes.LINE.toString() && prevSelected == currentShape) {
				if(two!=null) two = null; 
				else two = one; 
			}else two = null; 
			
			
			Point p = e.getPoint();
			try { 
				one = new Dot(p.x,p.y);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} 
			
			AddObject();
		}else if(e.getButton() == MouseEvent.BUTTON3) 
		{
			Point p = e.getPoint();
			if(p != null) {
				try {
					one = new Dot(p.x,p.y);
					select(one); 
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		}
	}
	
	public void delete() {
		for(Shape shape : selectedShapes) {
			execute(new RemoveShapeCmd(shape, model));
		}
		selectedShapes.clear();
	}
	
	public void edit() {
		if(selectedShapes.size() == 1) 
		{
			Shape obl =  dialog.showDialog(selectedShapes.get(0));
			if(obl!=null) {
				try {
					execute(new UpdateShapeCmd(selectedShapes.get(0), obl));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

	public void loadDrawing() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BIN files", "bin");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			model.getShapes().clear();
			undoStack.clear();
			redoStack.clear();
			selectedShapes.clear();
			model.setShapes(drawingFileStrategy.load(path));
			updateObservers();
			frame.repaint();
		}
	}

	public void saveDrawing() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BIN files", "bin");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			drawingFileStrategy.save(model.getShapes(), path + ".bin");
		}
	}

	public void saveLog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			logFileStrategy.save(frame.getTextArea().getText(), path + ".txt");
		}
	}

	public void loadLog() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			model.getShapes().clear();
			undoStack.clear();
			redoStack.clear();
			selectedShapes.clear();;
			String log = logFileStrategy.load(path);
			for (String line : log.split("\n")) {
				int selectedOption = JOptionPane.showConfirmDialog(null, "Execute command?\n" + line,
						"Execute command", JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					try {
						this.executeLine(line);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
			updateObservers();
			frame.repaint();
		}
	}
	
	private Shape stringToShape(String str) throws Exception {
		String[] splits = str.split("=");
		if(str.startsWith("Dot")) {
			int x = Integer.parseInt(splits[1].split(",")[0]);
			int y = Integer.parseInt(splits[2].split(",")[0]);
			int r = Integer.parseInt(splits[4].split(",")[0]);
			int g = Integer.parseInt(splits[5].split(",")[0]);
			int b = Integer.parseInt(splits[6].split("\\]")[0]);
			return new Dot(x, y, new Color(r,g,b));
		} else if(str.startsWith("Line")) {
			int startX = Integer.parseInt(splits[2].split(",")[0]);
			int startY = Integer.parseInt(splits[3].split("\\)")[0]);
			int endX = Integer.parseInt(splits[5].split(",")[0]);
			int endY = Integer.parseInt(splits[6].split("\\)")[0]);
			int r = Integer.parseInt(splits[8].split(",")[0]);
			int g = Integer.parseInt(splits[9].split(",")[0]);
			int b = Integer.parseInt(splits[10].split("\\]")[0]);
			return new Line(new Dot(startX, startY), new Dot(endX, endY), new Color(r,g,b));
		} else if(str.startsWith("Rectangle")) {
			int x = Integer.parseInt(splits[2].split(",")[0]);
			int y = Integer.parseInt(splits[3].split("\\)")[0]);
			int width = Integer.parseInt(splits[4].split(",")[0]);
			int height = Integer.parseInt(splits[5].split(",")[0]);
			int r = Integer.parseInt(splits[7].split(",")[0]);
			int g = Integer.parseInt(splits[8].split(",")[0]);
			int b = Integer.parseInt(splits[9].split("\\]")[0]);
			int fillR = Integer.parseInt(splits[11].split(",")[0]);
			int fillG = Integer.parseInt(splits[12].split(",")[0]);
			int fillB = Integer.parseInt(splits[13].split("\\]")[0]);
			return new Rectangle(new Dot(x, y), width, height, new Color(r,g,b), new Color(fillR, fillG, fillB));
		} else if(str.startsWith("Circle")) {
			int x = Integer.parseInt(splits[2].split(",")[0]);
			int y = Integer.parseInt(splits[3].split("\\)")[0]);
			int radius = Integer.parseInt(splits[4].split(",")[0]);
			int r = Integer.parseInt(splits[6].split(",")[0]);
			int g = Integer.parseInt(splits[7].split(",")[0]);
			int b = Integer.parseInt(splits[8].split("\\]")[0]);
			int fillR = Integer.parseInt(splits[10].split(",")[0]);
			int fillG = Integer.parseInt(splits[11].split(",")[0]);
			int fillB = Integer.parseInt(splits[12].split("\\]")[0]);
			return new Circle(new Dot(x, y), radius, new Color(r,g,b), new Color(fillR, fillG, fillB));
		} else if(str.startsWith("Donut")) {
			int x = Integer.parseInt(splits[2].split(",")[0]);
			int y = Integer.parseInt(splits[3].split("\\)")[0]);
			int radius = Integer.parseInt(splits[4].split(",")[0]);
			int innerRadius = Integer.parseInt(splits[5].split(",")[0]);
			int r = Integer.parseInt(splits[7].split(",")[0]);
			int g = Integer.parseInt(splits[8].split(",")[0]);
			int b = Integer.parseInt(splits[9].split("\\]")[0]);
			int fillR = Integer.parseInt(splits[11].split(",")[0]);
			int fillG = Integer.parseInt(splits[12].split(",")[0]);
			int fillB = Integer.parseInt(splits[13].split("\\]")[0]);
			return new Donut(new Dot(x, y), radius, innerRadius, new Color(r,g,b), new Color(fillR, fillG, fillB));
		} else if(str.startsWith("HexagonAdapter")) {
			int x = Integer.parseInt(splits[1].split(",")[0]);
			int y = Integer.parseInt(splits[2].split(",")[0]);
			int radius = Integer.parseInt(splits[3].split(",")[0]);
			int r = Integer.parseInt(splits[5].split(",")[0]);
			int g = Integer.parseInt(splits[6].split(",")[0]);
			int b = Integer.parseInt(splits[7].split("\\]")[0]);
			int fillR = Integer.parseInt(splits[9].split(",")[0]);
			int fillG = Integer.parseInt(splits[10].split(",")[0]);
			int fillB = Integer.parseInt(splits[11].split("\\]")[0]);
			return new HexagonAdapter(new Dot(x, y), radius, new Color(r,g,b), new Color(fillR, fillG, fillB));
		} 
		return null;
	}
	
	private Shape getShapeLike(Shape shape) {
		for(Shape s : model.getShapes()) {
			if(shape.equals(s))
				return s;
		}
		return null;
	}

	private void executeLine(String line) throws Exception {
		if(line.startsWith("Undo")) {
			this.undo();
		} else if(line.startsWith("Redo")) {
			this.redo();
		} else if (line.startsWith("Add")) {
			String shapeStr = line.substring(line.indexOf("shape=") + 6, line.lastIndexOf(']'));
			execute(new AddShapeCmd(stringToShape(shapeStr), model));
		} else if (line.startsWith("Remove")) {
			String shapeStr = line.substring(line.indexOf("shape=") + 6, line.lastIndexOf(']'));
			execute(new RemoveShapeCmd(getShapeLike(stringToShape(shapeStr)), model));
		} else if (line.startsWith("Update")) {
			String shapeStr = line.substring(line.indexOf("shape=") + 6, line.lastIndexOf(']'));
			try {
				execute(new UpdateShapeCmd(selectedShapes.get(0), stringToShape(shapeStr)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (line.startsWith("Select")) {
			String shapeStr = line.substring(line.indexOf("shape=") + 6, line.lastIndexOf(']'));
			Shape selected = getShapeLike(stringToShape(shapeStr));
			select(selected);
		} else if (line.startsWith("Deselect")) {
			String shapeStr = line.substring(line.indexOf("shape=") + 6, line.lastIndexOf(']'));
			Shape selected = getShapeLike(stringToShape(shapeStr));
			select(selected);
		} else if(line.startsWith("To Front")) {
			this.toFront();
		} else if(line.startsWith("To Back")) {
			this.toBack();
		} else if(line.startsWith("Bring To Front")) {
			this.bringToFront();
		} else if(line.startsWith("Bring To Back")) {
			this.bringToBack();
		} 
	}

	public void toFront() {
		if(selectedShapes.size() == 1 && model.indexOf(selectedShapes.get(0)) < model.size() - 1) {
			execute(new ToFrontCmd(selectedShapes.get(0), model));
		}
	} 	
	
	public void toBack() {
		if(selectedShapes.size() == 1 && model.indexOf(selectedShapes.get(0)) > 0) {
			execute(new ToBackCmd(selectedShapes.get(0), model));
		}
	} 
	
	public void bringToFront() {
		if(selectedShapes.size() == 1 && model.indexOf(selectedShapes.get(0)) < model.size() - 1) {
			execute(new BringToFrontCmd(selectedShapes.get(0), model));
		}
	} 
	
	public void bringToBack() {
		if(selectedShapes.size() == 1 && model.indexOf(selectedShapes.get(0)) > 0) {
			execute(new BringToBackCmd(selectedShapes.get(0), model));
		}
	} 
}
