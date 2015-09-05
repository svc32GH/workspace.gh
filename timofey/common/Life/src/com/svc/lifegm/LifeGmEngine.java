package com.svc.lifegm;

import java.awt.Point;
import java.util.Set;

import javax.swing.SwingUtilities;

import com.svc.lifegm.comp.LifePanel;
import com.svc.lifegm.form.LifeForm;

public class LifeGmEngine implements Runnable{
	
	private static final int timeInt = 1000; // mili-seconds
	
	private Set<Point> lifePoints;
	private LifePanel lifePanel;
	private LifeGmCalc lifeGmCalc;
	private LifeForm rootFrame;
	private boolean go = true;
	private boolean lifeContinue = true; 
	
	public LifeGmEngine(LifePanel lifePnl, LifeGmCalc lifeCalc) {
		this.lifePanel = lifePnl;
		this.lifePanel.setLifeEngine(this);
		this.lifeGmCalc = lifeCalc;
		this.rootFrame = (LifeForm) SwingUtilities.getRoot(this.lifePanel);
	}
	
	@Override
	public void run() {
		System.out.println("LifeGmEngine.run start");
		while (this.lifeContinue) {
			System.out.println("LifeGmEngine.run lifeContinue");
			try {
				goLifeGm();
				
//				goLifeGmSemen();
				
				Thread.sleep(timeInt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.rootFrame.exitLifeForm();
	}
	
	//////////////////////////////////////////////////
	// Semen, please work on this!!
	private void goLifeGmSemen() throws InterruptedException {
		lifePoints = lifePanel.getPoints();
		
		lifePoints = lifeGmCalc.getNext(lifePoints);
		lifePanel.setPoints(lifePoints); // repaint is here
		
		Thread.sleep(timeInt);
	}

	//////////////////////////////////////////////////
	// old demo version
	private void goLifeGm() throws InterruptedException {
		while(this.go && this.lifeContinue) {
			System.out.println("LifeGmEngine.run go");
			
			lifePoints = this.lifeGmCalc.getNext();
			lifePanel.setPoints(lifePoints); // repaint is here
			
			Thread.sleep(timeInt);
		} 
	}
	
	public boolean switchEngineState() {
		this.go = !this.go;
		return this.go;
	}
	
	public boolean isGo() {
		return go;
	}
	
	public void lifeStop() {
		this.lifeContinue = false;
	}

}
