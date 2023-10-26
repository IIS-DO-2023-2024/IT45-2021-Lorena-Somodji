package geometry;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PnlDrawing extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Shape> lista = new ArrayList<Shape>(); 
	private boolean isselect;
	
	public void add(Shape obl) 
	{
		if(obl != null) {
			lista.add(obl);
			this.repaint(); 
		}
	}
	
	public void paint(Graphics g) 
	{
			g.setColor(this.getBackground());
			g.fillRect(0, 0, g.getClipBounds().width ,  g.getClipBounds().height);
			
			if(lista.size()>0) {
				for(Shape obj : lista)
				{
					if(obj.getSelected())obj.draw(g,true);
					else obj.draw(g, false);
				}		
			}	
	}
	
	public PnlDrawing() 
	{
		super(); 
	}
	
	public void setSelected(int i) 
	{
		if(i >= 0 && i < lista.size()) {		
			Shape temp = lista.get(i);
			
			for(int j = i; j<lista.size()-1;j++) 
			{
				lista.set(j, lista.get(j+1));
			}
			
			lista.set(lista.size()-1, temp);
			
			isselect = true; 
		}else this.isselect = false; 
		
		this.repaint(); 
	}
	
	public void Delete() 
	{
		if(isselect) 
		{
			lista.remove(lista.size()-1);
			isselect = false; 
			
			repaint(); 
		}
	}
	
	public void Select(Point one) 
	{	
		for(int i = lista.size()-1; i>=0; i--)
		{
			if(lista.get(i).contains(one)) 
			{
				this.setSelected(lista.indexOf(lista.get(i)));
				return;
			}
		}
		
		this.setSelected(-1); 
	}
	
}

