import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	
	private JPanel panel = new JPanel();

	public static void main(String[] args) {
		Main mainModule = new Main();
		mainModule.run();
	}
	
	public void run() {
		System.out.println("Main.run --");
		
		FlowLayout flowLayoutManager = new FlowLayout();
		BorderLayout borderLayoutManager = new BorderLayout();
		
		JFrame frame = new JFrame("TimotyFrame");
		frame.setLayout(flowLayoutManager);
		Dimension preferredSize = new Dimension(500,500);
//		frame.setPreferredSize(preferredSize);
		frame.setSize(preferredSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton btn = new JButton("Timofey Button");
//		btn.setSize(new Dimension(130, 30));
		btn.setPreferredSize(new Dimension(130, 30));
		
		/////////////////////////////////////////////////////////////////////////////
		//
//		ButtonHandler btnHandler = new ButtonHandler();
//		btn.addActionListener(btnHandler);
		//
		/////////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////////////////
		//
		btn.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						System.out.println("Hello #2 for Timofey");
						System.out.println("panel.height = " + panel.getSize().height
								+ ", panel.width = " + panel.getSize().width);
					}
					
		});
		//
		/////////////////////////////////////////////////////////////////////////////
		
		frame.add(btn);
		
		JButton btn2 = new JButton();
		frame.add(btn2);
		
//		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setBackground(Color.CYAN);
		frame.add(panel);
		
		panel.setLayout(borderLayoutManager);
		
		JButton btnTop = new JButton("Top");
		JButton btnBottom = new JButton("Bottom");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		JButton btnCenter = new JButton("Center");
		
		panel.add(btnTop, BorderLayout.NORTH);
		panel.add(btnBottom, BorderLayout.SOUTH);
		panel.add(btnLeft, BorderLayout.WEST);
		panel.add(btnRight, BorderLayout.EAST);
		panel.add(btnCenter, BorderLayout.CENTER);

	}

}
