package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.Step;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author brightqin
 * @date 2018/6/18
 */

public class FrmModifyStep extends JDialog implements ActionListener {
    public Step step = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelName = new JLabel("步    骤    名：");
    private JTextField edtName = new JTextField(20);
    private JLabel labelPStime = new JLabel("计划开始时间：");
    private JTextField edtPStime = new JTextField(20);
    private JLabel labelPFtime = new JLabel("计划完成时间：");
    private JTextField edtPFtime = new JTextField(20);

    public FrmModifyStep(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPStime);
        workPane.add(edtPStime);
        workPane.add(labelPFtime);
        workPane.add(edtPFtime);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 280);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
            return;
        }
        String name = this.edtName.getText();
        if (!"".equals(name)) {
            step.setStepName(name);
        }
//            try {
//                ///
////                PersonPlanUtil.stepManager.modifyStep(step, String.valueOf(this.edtPStime.getText()),
////                        String.valueOf(this.edtPFtime.getText()));
//                this.setVisible(false);
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
    }
}
