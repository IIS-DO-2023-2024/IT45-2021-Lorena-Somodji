package paint;

import java.awt.*;

import javax.swing.*;

import geometry.Dot;
import geometry.Shape;

import java.util.*;

public class PnlDrawing extends JPanel {
	
	private ArrayList<Shape> lista; 
	private boolean isselect;
	private Dialog dijalog;
	
	public boolean getSelect()
	{
		return this.isselect;
	}

	public void setSelect(boolean select)
	{
		this.isselect = select;
		repaint();
	}
	
	public void add(Shape obl) 
	{ 
		if(obl != null) {
			lista.add(obl);
			this.isselect=false;
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
					if(isselect && lista.indexOf(obj)==lista.size()-1) obj.draw(g,true);
					else obj.draw(g, false);  
				}		
			}	
						
	}
	
	public PnlDrawing() 
	{
		super(); 
		dijalog = new Dialog();
		lista = new ArrayList<Shape>(); 
	}
	
	public void edit() 
	{
		if(isselect) 
		{
			Shape obl =  dijalog.showDialog(lista.get(lista.size()-1));
			if(obl!=null) {
				lista.set(lista.size()-1,obl);
				this.isselect = false;
			}
			
			repaint();
		}
	}
	
	public void delete() 
	{
		if(isselect) 
		{
			lista.remove(lista.size()-1);
			this.isselect = false;
			repaint();
		}
	}
	
	public void select(Dot one) 
	{
		for(int i = lista.size()-1; i>=0; i--)
		{
			if(lista.get(i).contains(one)) 
			{
				if(i == lista.size()-1) 
				{
					if(this.isselect) this.isselect = false;
					else this.isselect=true;
				}
				else 
				{   
					Shape temp = lista.get(i);
					for(int j = i; j<lista.size()-1;j++) 
					{
						lista.set(j, lista.get(j+1));
					}
					
					lista.set(lista.size()-1, temp);
					this.isselect = true;
				}
				repaint();
				return;
			}
		}
		
		this.isselect=false; 
		repaint();
	}
}
