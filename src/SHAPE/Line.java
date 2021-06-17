package SHAPE;

import java.awt.Graphics;
import java.awt.Point;

public class Line extends Shape{
	
	/*@Override
	public void setSelected(boolean selected) {
		this.Selected = selected;
	}
	*/
	protected Port pressPort;
	protected Port releasePort;

	
	public Line(Port pressPort,Port releasePort) {
		this.pressPort =  pressPort;
		this.releasePort =  releasePort;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point srcPt = pressPort.getLocation();
		Point dstPt = releasePort.getLocation();
		
		int dis = (int) srcPt.distance(dstPt);
		if (dis <= 0) {
			return;
		}
	}
	
	public void setReleasePort(Port newPort) {
		this.releasePort = newPort;
	}
	
	public void setPressPort(Port newPort) {
		this.pressPort = newPort;
	}
}
