package sort;

import javax.swing.DefaultListModel;
import geometry.Circle;

public class ModifiedListModel extends DefaultListModel {
	
	public ModifiedListModel() { super(); }
	
	public void Sort(int s) 
	{
		for(int i=0; i < this.size()-1; i++) 
		{
			for(int j = i+1; j<this.size(); j++) 
			{	
				if(this.get(i).getClass() == Circle.class &&  this.get(j).getClass() == Circle.class) 
				{			
					Circle ci = (Circle) this.get(i);
					Circle cj = (Circle) this.get(j);
					
					if(ci.compareTo(cj) == s) 
					{
						this.set(i, cj);
						this.set(j, ci);
					}
				}
			}
		}
	}
}

