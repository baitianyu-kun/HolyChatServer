/*
 * Created by JFormDesigner on Tue Feb 09 22:42:52 CST 2021
 */

package com.baitianyu.HolyChatServer.Utils;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author 123456789
 */
public class test extends JPanel {
    public test() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        comboBox1 = new JComboBox();

        //======== this ========

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(170, Short.MAX_VALUE)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                    .addGap(106, 106, 106))
                .addGroup(layout.createSequentialGroup()
                    .addGap(141, 141, 141)
                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(131, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField1;
    private JComboBox comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
