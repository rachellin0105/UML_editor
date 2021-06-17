package Mode;

import java.awt.event.MouseEvent;

import SHAPE.Shape;
import SHAPE.BasicObj;
import SHAPE.Class;
import SHAPE.Port;

public class ClassMode extends Mode {
	public ClassMode() {
		super("CLASSMODE");
	}
	
	
	/*
	 * create class object and set other objects unselected
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("ClassMode");
		super.mouseClicked(e);
		
		//set other objects unselected
		for(Shape shape : canvas.objs) {
			shape.setSelected(false);

		}	
		
		// create class object
		BasicObj classObj = new Class(e.getPoint());
		canvas.add(classObj);
		canvas.objs.add(classObj);
		
		for(Port port : classObj.getPorts()) {
			canvas.add(port);
		}
		
		canvas.repaint();
	}
}
