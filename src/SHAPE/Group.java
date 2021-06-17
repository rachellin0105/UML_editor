package SHAPE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Vector;

public class Group extends Shape{
	
	private Vector<Shape> children;
	
	private Dimension dim;
	
	public Group(Shape[] objs) {
		children = new Vector<Shape>();
		
		int left = objs[0].getX();
		int top = objs[0].getY();
		int right = left + objs[0].getWidth();
		int buttom =  top + objs[0].getHeight();
		for (Shape obj : objs) {
			Point point = obj.getLocation();
			left = point.x < left ? point.x : left;
			top = point.y < top ? point.y : top;
			right = point.x + obj.getWidth() > right ? point.x + obj.getWidth() : right;
			buttom = point.y + obj.getHeight() > buttom ? point.y+ obj.getHeight() : buttom;
			obj.setSelected(false);
			children.add(obj);

		}
		this.setSelected(true);
		this.dim = new Dimension(right-left,buttom-top);
		this.setBounds(left, top, dim.width, dim.height);
		
	}
	
	@Override
	public void setLocation(Point newP) {
		Point oldp = this.getLocation();
		
		Point offsetP = new Point(newP.x - oldp.x,newP.y - oldp.y);
		super.setLocation(new Point(oldp.x+offsetP.x,oldp.y+offsetP.y));
		for (Shape shape : children) {
			shape.setLocation(new Point(shape.getX()+offsetP.x,shape.getY()+offsetP.y));
		}
	}
	
	
	@Override
	public void paintBorder(Graphics g) {
		super.paintBorder(g);
		Graphics2D g2d = (Graphics2D) g;
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setColor(isSelected() ? Color.BLUE : Color.lightGray);
		g2d.setStroke(dashed);
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		g2d.dispose();
	}
	
	public Vector<Shape> getChildren(){
		return children;
	}
	
}
