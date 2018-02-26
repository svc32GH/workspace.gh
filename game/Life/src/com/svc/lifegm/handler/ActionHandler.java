package com.svc.lifegm.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.svc.lifegm.LifeGmEngine;
import com.svc.lifegm.comp.LifePanel;

public class ActionHandler implements ActionListener {
	
	private LifePanel lifePanel;
	private LifeGmEngine lifeEngine;
	private int pressNumber = 0;
	private Thread lifeThread;

//	public ActionHandler() { 
//		this.component = null;		
//	}
	
	public ActionHandler(LifePanel panel) {
		this.lifePanel = panel;		
		this.lifeEngine = this.lifePanel.getLifeEngine();
		this.lifeThread = new Thread(this.lifeEngine);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int height = this.lifePanel.getHeight();
		int width = this.lifePanel.getWidth();
		
		JComponent comp = (JComponent) e.getSource();
		int heightComp = comp.getHeight();
		int widthComp = comp.getWidth();
		
		System.out.println("height = " + height + "; width = " + width
				+ ";  heightComp = " + heightComp + "; widthComp = " + widthComp);
		
		if (comp.getClass().getSimpleName().equals("JButton")) {
			JButton btn = (JButton) comp;

			// Handling Start/Stop button
			if (btn.getName().equals("Start")) {
				if (pressNumber == 0) {
					btn.setText("Stop");
					this.lifeThread.start();
					pressNumber++;
				} else {
					// btn.getText().equals("Start") ? btn.setText("Stop") :
					// btn.setText("Start");

					if (btn.getText().equals("Start"))
						btn.setText("Stop");
					else
						btn.setText("Start");

					this.lifeEngine.switchEngineState();
				}
			}

			// Handling Exit button
			if (btn.getName().equals("Exit"))
				this.lifeEngine.lifeStop();
		}
	}

}
