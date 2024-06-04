package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;

public class DrawingFileImpl implements DrawingFileStrategy {

	@Override
	public ArrayList<Shape> load(String path) {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
			return (ArrayList<Shape>) objectInputStream.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public void save(ArrayList<Shape> shapes, String path) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(shapes);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
