package SHAPE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;



public class Class extends BasicObj {
	
	private final static int border = 2;
	private final static Dimension size = new Dimension(100,90);
	

	
	public Class(Point loc) {
		super(loc, size, "class");
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension textSize = getTextSize();
		g.setColor(Color.LIGHT_GRAY);
		
		int x = 0 + border;
		int y = 0 + border;
		int w = this.getWidth() - border*2;
		int h = this.getHeight() - border*2;
		
		g.fillRect(x, y, w, h);

		int offset = h / 3;
		g.setColor(Color.BLACK);
		g.drawLine(x, y + offset, x + w, y + offset);
		g.drawLine(x, y + offset * 2, x + w, y + offset * 2);

		g.drawString(this.getName(), x + w / 2 - textSize.width / 2, y + h / 3 - textSize.height / 2);
	}
	
	@Override
	public void paintBorder(Graphics g) {
		super.paintBorder(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(isSelected() ? Color.RED : Color.BLACK);
		g2d.setStroke(new BasicStroke(border));
		g2d.drawRect(border, border,this.getWidth() - border*2,  this.getHeight() - border*2);
		g2d.dispose();
	}
}
