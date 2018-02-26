package com.svc.lifegm;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class LifeGmCalc {

	private int cycleNum = 0;
	private Set<Point> lifePoints = new HashSet<Point>();
	private Set<Point> lifePoints2 = new HashSet<Point>();
	
	private Set<Point> alivePoints;
	private Set<Point> willBeAlivePoints = new HashSet<Point>();
	private Set<Point> bornPoints = new HashSet<Point>();
	private Set<Point> aroundCells = new HashSet<Point>();
	private Set<Point> blancCells = new HashSet<Point>();
	
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
	
	//////////////////////////////////////////////////
	// Semen, please work on this!!
	public Set<Point> getNext(Set<Point> lifePoints) {
		
		return null;
	}

	public Set<Point> getNext2(Set<Point> lifePoints) {
		this.alivePoints = lifePoints;
		this.willBeAlivePoints.clear();
		this.blancCells.clear();
//		Set<Point> bornCells = new HashSet<Point>();
		
		for (Point point : this.alivePoints) {
			setAroundCells(point);

			// alive cells ---------------------------------------------------\
			int nAliveAround = getCountAliveCellsAround();
			if ( nAliveAround == 2 || nAliveAround == 3 )
				this.willBeAlivePoints.add(point);
			
			// blanc cells around --------------------------------------------\
			Set<Point> blnkPts = getBlancPointsAround();
			this.blancCells.addAll(blnkPts);
		}
		
		// born cells --------------------------------------------------------\
		this.bornPoints.clear();
		for (Point point : this.blancCells) {
			setAroundCells(point); // set this.aroundCells
			int nAliveAround = getCountAliveCellsAround();
			if (nAliveAround == 3)
				bornPoints.add(point);
		}
		
		this.willBeAlivePoints.addAll(bornPoints);
		
		return this.willBeAlivePoints;
	}
	
	private Set<Point> getBlancPointsAround() {
		Set<Point> blancPoints = new HashSet<Point>();
		for (Point pnt : aroundCells) {
			if (!(this.alivePoints.contains(pnt)))
				blancPoints.add(pnt);
		}
		
		return blancPoints;
	}
	
	private int getCountAliveCellsAround() {
		int res = 0;
		for (Point pnt : aroundCells) {
			if (this.alivePoints.contains(pnt))
				res++;
		}
		return res;
	}
	
	private void setAroundCells(Point point) {
		Point point11 = new Point(point.x-1, point.y-1);
		Point point12 = new Point(point.x-1, point.y);
		Point point13 = new Point(point.x-1, point.y+1);
		Point point21 = new Point(point.x,   point.y-1);
		Point point23 = new Point(point.x,   point.y+1);
		Point point31 = new Point(point.x+1, point.y-1);
		Point point32 = new Point(point.x+1, point.y);
		Point point33 = new Point(point.x+1, point.y+1);
		
		aroundCells.clear();
		
		aroundCells.add(point11);
		aroundCells.add(point12);
		aroundCells.add(point13);
		aroundCells.add(point21);
		aroundCells.add(point23);
		aroundCells.add(point31);
		aroundCells.add(point32);
		aroundCells.add(point33);
		
	}

}
