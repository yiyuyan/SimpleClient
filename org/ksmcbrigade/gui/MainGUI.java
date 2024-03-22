package org.ksmcbrigade.gui;

import org.ksmcbrigade.hacks.Hack;
import org.ksmcbrigade.utils.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI extends JFrame {

    public static boolean init = false;

    public static MainGUI THIS;

    public MainGUI(int x, int y) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Simple Client hacks list");
        setSize(300, 400);

        if(x<0||y<0){
            setLocationRelativeTo(null);
        }
        else{
            setLocation(x,y);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        Font font = new Font("Arial", Font.PLAIN, 12);
        for (Category category : Category.values()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.add(new JLabel(category.name()));

            for(Hack hack:category.getList()){
                JCheckBox checkBox = new JCheckBox(hack.name);
                checkBox.setSelected(hack.enabled);
                checkBox.setFont(font);
                checkBox.addActionListener(e -> {
                    try {
                        hack.setEnabled(checkBox.isSelected());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                panel.add(checkBox);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            //scrollPane.setPreferredSize(new Dimension(50, 200));
            mainPanel.add(scrollPane);
        }

        add(mainPanel);

        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                init = false;
            }
        });
        pack();
        setVisible(true);
        init = true;
        THIS = this;
    }
}
