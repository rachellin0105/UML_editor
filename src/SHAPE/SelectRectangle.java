package SHAPE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;



public class SelectRectangle extends Shape {

	/**
	 * Initialize component by top-left corner
	 * 
	 * @param p - top-left corner
	 */
	public SelectRectangle(Point p) {
		this.setLocation(p);
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);

		g2d.setPaint(Color.GRAY);
		g2d.setStroke(dashed);
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.dispose();
	}

	public boolean contains(Shape shape) {
		// TODO Auto-generated method stub
		// System.out.println(shape.getX());
		return this.getX() < shape.getX() && this.getY()<shape.getY() && 
				this.getX()+this.getWidth() > shape.getX()+shape.getWidth() && this.getY()+this.getHeight() > shape.getY()+shape.getHeight();
	}
	



}
