package SHAPE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class AssociationLine extends Line {
	
	public AssociationLine(Port pressPort,Port releasePort){
		super(pressPort,releasePort);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Point pressPt = pressPort.getLocation();
		Point releasePt = releasePort.getLocation();
		g2.drawLine(pressPt.x, pressPt.y, releasePt.x, releasePt.y);
		
		double angle = Math.atan2(releasePt.y-pressPt.y, releasePt.x-pressPt.x);
		AffineTransform tx = g2.getTransform();
	    tx.translate(releasePt.x, releasePt.y);
	    tx.rotate((angle-Math.PI/2)); 
	    
	    g2.setColor(Color.BLACK);
	    g2.setTransform(tx);
	    
	    g2.drawLine(0,0,-5, -10);
	    g2.drawLine(0,0,5, -10);
	    g2.dispose();
	}

}
