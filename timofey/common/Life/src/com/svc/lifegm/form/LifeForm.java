package com.svc.lifegm.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;

import com.svc.lifegm.LifeGmCalc;
import com.svc.lifegm.LifeGmEngine;
import com.svc.lifegm.comp.LifePanel;
import com.svc.lifegm.handler.ActionHandler;
import com.svc.lifegm.handler.MouseHandler;

public class LifeForm extends JFrame {
	
	private static final int DEF_WIDTH = 500;
	private static final int DEF_HEIGHT = 500;
	private static final String TITLE = "Game - Life";
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private LifePanel lifePanel;
	
	private JButton btnStart;
	private JButton btnExit;
	
	private LifeGmEngine lifeEngine;
	private LifeGmCalc lifeGmCalc = new LifeGmCalc();

	/**
	 * 
	 */
	private static final long serialVersionUID = -7021043584638593137L;
	
	public LifeForm() {
		setSize(DEF_WIDTH, DEF_HEIGHT);
		setTitle(TITLE);
		
		createPanels(30, 30, Color.MAGENTA, Color.YELLOW, this, 15F);
		lifeEngine = new LifeGmEngine(lifePanel, lifeGmCalc);
		createButtons();
	}
	
	public void exitLifeForm() {
		System.exit(0);
	}
	
//	height = 402; width = 424
//	height = 646; width = 1306

	private void createPanels(int width, int height, Color colorBorder, Color colorCenter, LifeForm lifeForm, float squareD) {
		Dimension prefSize = new Dimension(width, height);
		
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(prefSize);
		leftPanel.setBackground(colorBorder);
		
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(prefSize);
		rightPanel.setBackground(colorBorder);
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(prefSize);
		topPanel.setBackground(colorBorder);
		

		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(prefSize);
		bottomPanel.setBackground(colorBorder);
		
		
		lifeForm.add(leftPanel, BorderLayout.WEST);
		lifeForm.add(rightPanel, BorderLayout.EAST);
		lifeForm.add(topPanel, BorderLayout.NORTH);
		lifeForm.add(bottomPanel, BorderLayout.SOUTH);
		
		this.lifePanel = new LifePanel(colorCenter, squareD);
		MouseHandler msHandler = new MouseHandler(lifePanel);
		lifePanel.addMouseListener(msHandler);
		lifeForm.add(lifePanel, BorderLayout.CENTER);

//		Component c = lifePanel.getParent();
//		String WindowAncestor1 = c.getClass().getName();
//		Component d = SwingUtilities.getWindowAncestor(c);
//		String WindowAncestor2 = d.getClass().getName();
//		Component e = SwingUtilities.getRoot(lifePanel);
//		String WindowAncestor3 = e.getClass().getName();
//		
//		String rootPaneRootPaneName = lifePanel. getRootPane().getRootPane().getClass().getName();
//		System.out.println(WindowAncestor1);
//		System.out.println(WindowAncestor2);
//		System.out.println(WindowAncestor3);
//		System.out.println(rootPaneRootPaneName);
//		System.out.println(rootPaneRootPaneName);
		

	}
	
	public void createButtons() {
//		height = 10; width = 34
		Dimension btnPreferredSize = new Dimension(70, 20);
		ActionHandler btnAction = new ActionHandler(lifePanel);

		btnStart = new JButton();
		btnStart.setPreferredSize(btnPreferredSize);
		btnStart.setText("Start");
		btnStart.setName("Start");
		btnStart.addActionListener(btnAction);

		btnExit = new JButton();
		btnExit.setPreferredSize(btnPreferredSize);
		btnExit.setText("Exit");
		btnExit.setName("Exit");
		btnExit.addActionListener(btnAction);
		
		String name = btnStart.getName();
		String canonicalName = btnStart.getClass().getCanonicalName();
		String className = btnStart.getClass().getName();
		String simpleClassName = btnStart.getClass().getSimpleName();
//		String ComponentType = btnStart.getClass().getComponentType().getName();
		System.out.println(name + " " + canonicalName + " " + className + " " + simpleClassName);
		
		bottomPanel.add(btnStart);
		bottomPanel.add(btnExit);
	}

	public LifeGmEngine getLifeEngine() {
		return lifeEngine;
	}

}
