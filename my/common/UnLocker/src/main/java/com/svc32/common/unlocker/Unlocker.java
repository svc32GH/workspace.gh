package com.svc32.common.unlocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Unlocker extends JFrame {
    //    3-rd Monitor:
//    private static final int x = 4200;
//    private static final int y = 655;

    //    1-st Monitor:
//    private static final int x = 1370;
//    private static final int y = 15;

    //    Single Home Monitor, right-midle:
//    private static final int x = 1174;
//    private static final int y = 133;

    //    Note ProBook 4710s
    private static final int x = 1086;
    private static final int y = 7;

//    //    1-st Monitor, right-midle:
//    private static final int x = 680;
//    private static final int y = 670;
//
    //    2-nd Monitor:
//    private static final int x = 2575;
//    private static final int y = 675;

    private static final int width = 500;
    private static final int height = 500;

    private JFrame thisFrame;
    private JList listBox;
    private UnlockLogWriter ulw;
    private Thread ulwThread;
    private File logFile;

    public Unlocker(String logFilePath) {
        constructFrame(new File(logFilePath));
        startUnlocker();
    }

    public Unlocker(File logFile) {
        constructFrame(logFile);
        startUnlocker();
    }

    private void startUnlocker() {
        ulw = new UnlockLogWriter(this.logFile);
        ulwThread = new Thread(ulw);
        ulwThread.start();
    }

    private void constructFrame(File logFile) {
        this.logFile = logFile;
        this.setTitle("Unlock Monitor");
        this.setBounds(x, y, width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        thisFrame = this;
        final JButton btn = new JButton("Get Position");
        btn.addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                      Rectangle btnBounds = thisFrame.getBounds();
                                      System.out.println( "Frame:   x = " + btnBounds.x + ", y = " + btnBounds.y + ", w = " + thisFrame.getWidth() + ",h = " + thisFrame.getHeight() );
                                      System.out.println( "ListBox:                   w = " + listBox.getWidth() + ",h = " + listBox.getHeight() );
                                      ulw.stopRunning();
                                      ulwThread.interrupt();
                                  }
                              }
        );

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBackground(new Color(163, 220, 166));

        JPanel pnlMainLeft = new JPanel();
        JPanel pnlMainRight = new JPanel();
        JPanel pnlMainTop = new JPanel();
        JPanel pnlMainBot = new JPanel();
        pnlMainLeft.setOpaque(false);
        pnlMainRight.setOpaque(false);
        pnlMainTop.setOpaque(false);
        pnlMainBot.setOpaque(false);

        JPanel pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(30, 50));
        pnlTop.setBackground(new Color(68, 168, 185));

        JPanel pnlBot = new JPanel();
        pnlBot.setPreferredSize(new Dimension(30, 50));
        pnlBot.setBackground(new Color(138, 100, 94));
        pnlBot.add(btn);

        final DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);
        listBox = list;

        for (int i = 0; i < 25; i++) {
            listModel.addElement("Item element " + i);
        }

        pnlMain.add(pnlMainLeft, BorderLayout.WEST);
        pnlMain.add(pnlMainRight, BorderLayout.EAST);
        pnlMain.add(pnlMainTop, BorderLayout.NORTH);
        pnlMain.add(pnlMainBot, BorderLayout.SOUTH);

        pnlMain.add(new JScrollPane(list), BorderLayout.CENTER);


        Container cn = this.getContentPane();
//        cn.setLayout(new FlowLayout());
//        cn.add(btn);
        cn.add(pnlTop, BorderLayout.NORTH);
        cn.add(pnlMain, BorderLayout.CENTER);
        cn.add(pnlBot, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        //        this.getBounds()

    }

}
