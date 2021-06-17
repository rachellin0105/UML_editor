package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import SHAPE.AssociationLine;
import SHAPE.CompositionLine;
import SHAPE.GeneralizationLine;
import SHAPE.Line;
import SHAPE.LineFactory;
import SHAPE.Port;
import SHAPE.Shape;
import UI.Buttons;

public class LineMode extends Mode {
	
	private Port pressPort;
	private Port releasePort;
	private Line line;
	public LineMode(String LineName) {
		super(LineName);
		pressPort = null;
		line = null;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseMoved(e);
		Point moveP;
		
		for (Shape shape : canvas.objs ) {
			Port[] ports = shape.getPorts();
			moveP = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), shape);
			shape.setSelected(shape.contains(moveP));
			if( ! shape.contains(moveP)) {
				if (ports != null) { // subclass of shape can have no port¡Alike group
					for ( Port port : ports) {
						moveP = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), port);
						port.setSelected(port.contains(moveP));
					}
				}

			}
		}
		
		canvas.repaint();
		
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		System.out.println(canvas.mode.getName()+ " Line Mode");
		Point pressP = e.getPoint();
		
		for (Shape shape : canvas.objs ) {
			Port[] ports = shape.getPorts();
			if (ports != null) {
				for ( Port port : ports) {
					pressP = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), port);
					if (port.contains(pressP)) {
						port.setSelected(true);
						pressPort = port;
					}
					else {
						port.setSelected(false);
					}
				}
			}
			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point dragPoint = e.getPoint();
		Port tempPort = new Port(null,dragPoint);
		
		for (Shape shape : canvas.objs ) {
			Port[] ports = shape.getPorts();
			dragPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), shape);
			shape.setSelected(shape.contains(dragPoint));
			if( ! shape.contains(dragPoint)) {
				if (ports != null) {
					for ( Port port : ports) {
						dragPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), port);
						port.setSelected(port.contains(dragPoint));
					}
				}

			}
		}
		
		if (pressPort != null ) {
			if(line == null) {
				line = LineFactory.createLine(canvas.mode.getName(),pressPort,tempPort);
				line.setSize(canvas.getWidth(), canvas.getHeight());
				canvas.add(line,0);
			}
			line.setPressPort(pressPort);
			line.setReleasePort(tempPort);
		}

		canvas.repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		Point releasePoint = e.getPoint();
		releasePort = null;
		
		for (Shape shape : canvas.objs ) {
			Port[] ports = shape.getPorts();
			if( ports != null ) {
				for ( Port port : ports) {
					releasePoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), port);
					port.setSelected(port.contains(releasePoint));
					if (pressPort != null && port.contains(releasePoint) && port.getParentObjs() != pressPort.getParentObjs()) {
						releasePort = port;
					}
				}
			}
		}
		
		if (line != null ) {
			if( releasePort == null) {
				canvas.remove(line);
			}
			else{
				line.setReleasePort(releasePort);
				pressPort.addLine(line);
				releasePort.addLine(line);
			}
		}
		line = null;
		pressPort = null;
		canvas.repaint();
	}
}
