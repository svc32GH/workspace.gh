package com.svc.lifegm.handler;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;


import com.svc.lifegm.comp.LifePanel;

public class MouseHandler extends MouseAdapter {
	
	private LifePanel lifePanel;
	
	public MouseHandler(LifePanel _lifePanel) {
		this.lifePanel = _lifePanel; 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		int x = p.x / 15;
		int y = p.y / 15;
		Point n = new Point(x,y);
		lifePanel.setPoint(n);
		lifePanel.repaint();
		System.out.println("p = " + p.toString());
		System.out.println("n = " + n.toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
