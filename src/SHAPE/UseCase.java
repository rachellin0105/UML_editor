package SHAPE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import SHAPE.BasicObj;

public class UseCase extends BasicObj {
	
	private final static Dimension size = new Dimension(120,70);
	public final static int border = 2;
	
	public UseCase(Point loc) {
		super(loc, size, "UseCase");
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension textSize = getTextSize();
		g.setColor(Color.GRAY);
		g.fillOval(border, border, this.getWidth() - border * 2, this.getHeight() - border * 2);

		g.setColor(Color.WHITE);
		g.drawString(this.getName(), border + (this.getWidth() - border * 2 - textSize.width) / 2, border + ( this.getHeight() - border * 2 + textSize.height) / 2);
	}

	@Override
	public void paintBorder(Graphics g) {
		super.paintBorder(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(isSelected() ? Color.RED : Color.BLACK);
		g2d.setStroke(new BasicStroke(border));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // make border smooth
		g2d.drawOval(border, border, this.getWidth() - border * 2, this.getHeight() - border * 2);
		g2d.dispose(); // delete  g2d
	}
}
