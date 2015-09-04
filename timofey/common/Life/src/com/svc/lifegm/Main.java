package com.svc.lifegm;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.HashSet;

import javax.swing.JFrame;

import com.svc.lifegm.form.LifeForm;

public class Main {

	public static void main(String[] args) {
		Main _main = new Main();
		_main.run();
		_main.test();
		
	}
	
	private void test() {
		Point p1 = new Point(7,8);
		Point p2 = new Point(8,8);
		Point p3 = new Point(7,8);
		Point p4 = new Point(8,9);
		Point p5 = new Point(8,8);
		HashSet<Point> set = new HashSet<Point>();
		set.add(p1);
		set.add(p2);
		set.add(p3);
		set.add(p4);
		set.add(p5);
		
//		System.out.println(p1 == p3 ? "p1 = p3" : "p1 != p3" + "; count = " + set.size() + "\nset = " + set);
		
	}

	private void run() {
		System.out.println("package com.svc, class Main.");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LifeForm lifeForm = new LifeForm();
				lifeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				lifeForm.setVisible(true);
				
				lifeForm.pack();
			}
		}

		);
	}
	
}
