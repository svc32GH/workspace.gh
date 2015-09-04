package com.svc.lifegm.comp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.svc.lifegm.LifeGmEngine;

public class LifePanel extends JPanel {

	private static final int DEF_WIDTH = 303;
	private static final int DEF_HEIGHT = 303;
	private float squareD;

	// r,g,b = 170, 230, 255 - blank square (variant)
	// r,g,b = 210, 240, 255 - blank square
	// r,g,b = 168,   0,  84 - alive square (variant)
	// r,g,b = 220,   0, 110 - alive square
    private static final Color blancC = new Color(210, 240, 255);
    private static final Color aliveC = new Color(220,   0, 110);
	
    private LifeGmEngine lifeEngine;
	private Set<Point> alivePoints = new HashSet<Point>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593807806938822192L;
	
	public LifePanel(Color color, float dSquare) {
		this.squareD = dSquare;
		Dimension preferredSize = new Dimension(DEF_WIDTH, DEF_HEIGHT);
		setPreferredSize(preferredSize);
		setSize(DEF_WIDTH, DEF_HEIGHT);
		setBackground(color);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		paintRectangles(g);
	}
	
	private void paintRectangles(Graphics g) {
		Rectangle2D[][] rects;
		Graphics2D g2 = (Graphics2D) g;
		float width = squareD;
		float height = squareD;
		
		rects = new Rectangle2D[20][20];
		for (int i=0; i<20; i++) {
			float leftX = squareD * i + 1;
			for (int j=0; j<20; j++) {
				float topY = squareD * j + 1;
				
				Rectangle2D rect = new Rectangle2D.Float(leftX, topY, width, height);
				rects[i][j] = rect;
				g2.setStroke(new BasicStroke(3.0F));
				
				// r,g,b = 170, 230, 255 - blank square (variant)
				// r,g,b = 210, 240, 255 - blank square
				// r,g,b = 168,   0,  84 - alive square
				Point r = new Point(i, j);
				g2.setColor(hasSquareAlive(r) ? aliveC : blancC);
				g2.fill(rect);
				g2.setColor(Color.BLACK);
				g2.draw(rect);
			}
		}
	}
	
	public void setPoint(Point point) {
		if (alivePoints.contains(point))
			alivePoints.remove(point);
		else
			alivePoints.add(point);
	}
	
	public void setPoints(Set<Point> points) {
		alivePoints.clear();
		alivePoints.addAll(points);
//		this.paint(this.getGraphics());
		repaint();
	}
	
	public boolean hasSquareAlive(Point point) {
		return alivePoints.contains(point);
	}

	public LifeGmEngine getLifeEngine() {
		return lifeEngine;
	}

	public void setLifeEngine(LifeGmEngine lifeEngine) {
		this.lifeEngine = lifeEngine;
	}

}
