package Mode;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.SwingUtilities;

import SHAPE.SelectRectangle;
import SHAPE.Shape;

public class SelectMode extends Mode {
	
	/*
	 * Point = old location
	 */
	private Map<Shape,Point> selectedObjs;
	
	private SelectRectangle selectRectangle;
	private Point pressP;
	//private Point releaseP;
	private boolean pressOnObj;
	
	public SelectMode() {
		super("SELECT");
		//System.out.println("SelectMode");
		selectedObjs = new HashMap<Shape,Point>();
		pressP = new Point();
		//releaseP = new Point();
		pressOnObj = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		pressP = e.getPoint();
		boolean pressOnSelObjs = false;
		
		for (Entry<Shape,Point> selShape : selectedObjs.entrySet()) {
			Point pressP = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), selShape.getKey());
			if (selShape.getKey().contains(pressP)) {
				pressOnSelObjs = true;
				pressOnObj = true;
			}
			// update selectedObjs location
			selectedObjs.put(selShape.getKey(),selShape.getKey().getLocation());
		}
		
		if (! pressOnSelObjs) {
			selectedObjs.clear();
			for (Shape shape : canvas.objs) { 
				// convert point from Canvas to Shape
				Point pressP = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), shape);
				if (shape.contains(pressP) && ! pressOnObj) {
					pressOnObj = true;
					selectedObjs.put(shape,shape.getLocation());
					//System.out.println(selectedObjs);
				}
				shape.setSelected(selectedObjs.containsKey(shape));
			}
		}
		
		
		if ( ! pressOnObj ) {
			selectRectangle =  new SelectRectangle(e.getPoint());
			canvas.add(selectRectangle);
			selectedObjs.clear();
		}
			
		canvas.resetDepth();
		canvas.repaint();

	}
	
	
	/*
	 * get last selected object and repaint on dragged Point
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		Point draggedP = e.getPoint();
		if ( ! pressOnObj ) { // draw selectRectangle
			selectRectangle.setBounds(Math.min(draggedP.x, pressP.x), Math.min(draggedP.y, pressP.y),
					Math.abs(draggedP.x - pressP.x), Math.abs(draggedP.y - pressP.y));
			
			for (Shape shape : canvas.objs) {
				if (selectRectangle.contains(shape)) {
					shape.setSelected(true);
					selectedObjs.put(shape,shape.getLocation());
				}
				else {
					shape.setSelected(false);
					selectedObjs.remove(shape,shape.getLocation());
				}

			}
		}
		else { // drag selected objects

			for (Entry<Shape,Point> selShape : selectedObjs.entrySet()) {
				Point oldp = selShape.getValue();
				int moveX = draggedP.x - pressP.x;
				int moveY = draggedP.y - pressP.y;
				selShape.getKey().setLocation(new Point(oldp.x+moveX,oldp.y+moveY));
			}
		}
		
		canvas.repaint();
			
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//selectedObjs.clear();
		if ( selectRectangle != null) 
			canvas.remove(selectRectangle);
		canvas.repaint();
		pressOnObj = false;
		canvas.resetDepth();
	}

}
