package com.svc.lifegm;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class LifeGmCalc {

	private int cycleNum = 0;
	private Set<Point> lifePoints = new HashSet<Point>();
	private Set<Point> lifePoints2 = new HashSet<Point>();
	
	public LifeGmCalc() {
		Point p = new Point(10, 10);
		lifePoints.add(p);
		
		p = new Point(11, 10);
		lifePoints.add(p);
		
		p = new Point(12, 10);
		lifePoints.add(p);
		
		p = new Point(11, 9);
		lifePoints2.add(p);
		
		p = new Point(11, 10);
		lifePoints2.add(p);
		
		p = new Point(11, 11);
		lifePoints2.add(p);
	}
	
	public Set<Point> getNext() {
		cycleNum++;
		int ost = cycleNum % 2;
		if (ost == 1)
			return lifePoints;
		else
			return lifePoints2;
	}

}
