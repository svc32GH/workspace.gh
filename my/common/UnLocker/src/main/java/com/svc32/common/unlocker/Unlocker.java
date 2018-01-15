package com.svc32.common.unlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Unlocker extends JFrame {
    private static final int x = 4200;
    private static final int y = 655;
//    private static final int x = 1370;
//    private static final int y = 15;
    private static final int width = 300;
    private static final int height = 300;

    public JFrame thisFrame;

    public Unlocker() {
        super("Unlock Monitor");
        this.setBounds(x, y, width, height);
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

        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout(5, 5));
        pnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        final DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        pnl.add(new JScrollPane(list), BorderLayout.WEST);
        for (int i = 0; i < 25; i++) {
            listModel.addElement("Элемент списка " + i);
        }

        pnl.add(list);

        Container cn = this.getContentPane();
        cn.setLayout(new FlowLayout());
//        cn.add(btn);
        cn.add(pnl);


        //        this.getBounds()
    }

}
