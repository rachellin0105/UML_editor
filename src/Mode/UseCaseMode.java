package Mode;

import java.awt.event.MouseEvent;

import SHAPE.BasicObj;
import SHAPE.Class;
import SHAPE.Port;
import SHAPE.Shape;
import SHAPE.UseCase;

public class UseCaseMode extends Mode {
	
	public UseCaseMode() {
		super("UseCase");
	}
	/*
	 * create class object and set other objects unselected
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("UseCaseMode");
		super.mouseClicked(e);
		
		//set other objects unselected
		for(Shape shape : canvas.objs) {
			shape.setSelected(false);

		}	
		
		// create class object
		BasicObj classObj = new UseCase(e.getPoint());
		canvas.add(classObj);
		canvas.objs.add(classObj);
		
		for(Port port : classObj.getPorts()) {
			canvas.add(port);
		}
		
		canvas.repaint();
	}
}
