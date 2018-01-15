package com.svc32.common.unlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Unlocker extends JFrame {

    public JFrame thisFrame;

    public Unlocker() {
        super("Unlock Monitor");
        this.setBounds(4200, 655, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        thisFrame = this;
        final JButton btn = new JButton("Get Position");
        btn.addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                      Rectangle btnBounds = thisFrame.getBounds();
                                      System.out.println( "x = " + btnBounds.x );
                                      System.out.println( "y = " + btnBounds.y );
                                  }
                              }
        );

        Container cn = this.getContentPane();
        cn.setLayout(new FlowLayout());
        cn.add(btn);


        //        this.getBounds()
    }

}
