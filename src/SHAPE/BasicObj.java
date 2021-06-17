package SHAPE;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;


public class BasicObj extends Shape{
	
	private Port ports[];
	private final int numOfPorts = 4;
	
	public BasicObj(Point loc,Dimension size,String Name){
		this.setName(Name);
		this.ports = new Port[numOfPorts];
		this.setBounds(loc.x, loc.y, size.width, size.height);
		
		for (int i = 0; i < numOfPorts; ++i) {
			ports[i] = new Port(this,this.getLocation());
		}
		setPortLaction();
		this.setSelected(true);
	}

	private void setPortLaction() {
		Dimension size = this.getSize();
		for(int i = 0; i < this.numOfPorts ; i++ ) {
			switch(i) {
			case 0: // Top
				ports[i].setLocation(new Point( this.getX()+size.width/2, this.getY()-Port.height ));
				break;
			case 1: // Right
				ports[i].setLocation(new Point( this.getX()+size.width , this.getY()+size.height/2 )); 
				break;
			case 2: // bottom
				ports[i].setLocation(new Point( this.getX()+size.width/2 , this.getY()+size.height )); 
				break;
			case 3: // Left
				ports[i].setLocation(new Point( this.getX()-Port.width , this.getY()+size.height/2 )); 
				break;
			}
		}
	}
	
	public Dimension getTextSize() {
		Dimension size = new Dimension();
		Graphics g = this.getGraphics();
		FontMetrics fm = g.getFontMetrics(g.getFont());
		size.width = fm.stringWidth(this.getName());
		size.height = fm.getHeight();

		return size;
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		for (Port port : this.ports) {
			port.setVisible(selected);
		}
	}
	
	public Port[] getPorts() {
		return ports;
	}
	
	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
		setPortLaction();
	}
	
}
