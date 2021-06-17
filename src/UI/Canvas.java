package UI;

import java.awt.Component;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Mode.Mode;
import SHAPE.BasicObj;
import SHAPE.Group;
import SHAPE.Port;
import SHAPE.Shape;

public class Canvas extends JPanel{
	/*
	 *  Java - singleton pattern 
	 *   only one Object will be generate
	 */
	private static Canvas uniqueInstance = null;

	public Vector<Shape> objs;
	
	public Mode mode;
	
	private Canvas() {
		System.out.println("Canvas constructor");
		this.setLayout(null);
		objs = new Vector<Shape>();
	}
	
	// synchronized to avoid multi-threads progressing.
	public static synchronized Canvas getInstance() {
		if(uniqueInstance==null) { // generating for the first time
			uniqueInstance = new Canvas();
		}
		return uniqueInstance;
	}

	public void setMode(Mode mode) {
		this.removeMouseListener(this.mode);
		this.removeMouseMotionListener(this.mode);
		this.mode = mode;
		this.addMouseListener(this.mode);
		this.addMouseMotionListener(this.mode);
	}
	
	private Comparator<JComponent> zOrderCmp = new Comparator<JComponent>() {
		public int compare(JComponent arg0, JComponent arg1) {
			return getComponentZOrder(arg0) - getComponentZOrder(arg1);
		}
	};

	public Shape[] getSelectedObjs() {
		return objs.stream().filter(e -> e.isSelected()).toArray(Shape[]::new);
	}
	
	public int getZorder(Shape shape) {
		// TODO Auto-generated method stub
		return this.getComponentZOrder(shape);
	}
	
	/**
	 * Change z-orders of all selected components on canvas
	 */
	public void resetDepth() {
		boolean unsort = false; // check order of objs need to be sorted.

		// move selected objs to front
		for (int i = objs.size() - 1; i >= 0; --i) { // reverse move to front, make sure z-order wouldn't be reversed
			Shape obj = objs.get(i);
			if (obj.isSelected()) {
				unsort = true;
				
				Vector<Shape> children = obj.getChildren();
				if ( children != null) {
					for (Shape shape : children) {
						setComponentZOrder(shape, 0);
						Port[] ports = shape.getPorts();
						if (ports != null) {
							for (Port p: ports) {
								setComponentZOrder(p,0);
							}
						}
					}
				}
				
				Port[] ports = obj.getPorts();
				if (ports != null) {
					for (Port p: ports) {
						setComponentZOrder(p,0);
					}
				}
				
				setComponentZOrder(obj, 0);
			}
		}

		if (unsort) { // sort objs, ports and groups vector by z-order
			objs.sort(zOrderCmp);
		}
		this.repaint();
	}
	
	
	public void showObjs() {
		for (Shape shape : objs) {
			System.out.println(shape);
		}
	}

}
