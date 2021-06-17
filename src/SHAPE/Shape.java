package SHAPE;

import java.awt.Point;
import java.util.Vector;

import javax.swing.JComponent;


public class Shape extends JComponent{
	
	private boolean Selected;
	
	
	public boolean isSelected() {
		return Selected;
	}
	
	public void setSelected(boolean selected) {
		this.Selected = selected;
	}
	
	
	public Port[] getPorts() {
		return null;
	}
	
	public Vector<Shape> getChildren(){
		return null;
	} 
}
