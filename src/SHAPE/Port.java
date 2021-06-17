package SHAPE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Port extends Shape{
	private BasicObj parent;
	private Vector<Line> Lines;
	private Color color;
	public static final int width = 10;
	public static final int height = 10;

	public Port(BasicObj parent,Point loc) {
		this.parent = parent;
		color = Color.BLACK;
		this.setBounds(loc.x, loc.y, width, height);
		Lines = new Vector<Line>();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(isSelected() ? Color.RED : Color.BLACK);
		g.fillRect(0, 0, width,height);
	}
	
	public void addLine(Line line) {
		Lines.add(line);
	}
	
	public BasicObj getParentObjs() {
		return parent;
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		this.setVisible(selected);
	}
}
