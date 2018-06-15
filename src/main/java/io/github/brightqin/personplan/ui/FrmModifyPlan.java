package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.DataCheckUtil;
import io.github.brightqin.personplan.util.PersonPlanUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author brightqin
 * @date 2018/6/13
 */

public class FrmModifyPlan extends JDialog implements ActionListener {
    public Plan plan = null;
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JTextField edtName = new JTextField(20);
    private JTextField edtDeadline = new JTextField(20);

    public FrmModifyPlan(Frame f, String s, boolean b) {
        super(f, s, b);
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        JLabel labelName = new JLabel("计划名：");
        JPanel workPane = new JPanel();
        workPane.add(labelName);
        workPane.add(edtName);
        JLabel labelDeadline = new JLabel("截止时间：");
        workPane.add(labelDeadline);
        workPane.add(edtDeadline);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 180);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
        // 事件监听
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        }
        if (e.getSource() == this.btnOk) {
            if ("".equals(this.edtName.getText())) {
                plan.setPlanName(this.edtName.getText());
            }
            try {
                if (!"".equals(this.edtDeadline.getText())) {
                    plan.setDeadLine(DataCheckUtil.convertDate(this.edtDeadline.getText()));
                }
                PersonPlanUtil.planManager.modifyPlan(plan);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

