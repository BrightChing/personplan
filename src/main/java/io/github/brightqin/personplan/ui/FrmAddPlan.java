package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.PersonPlanUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author brightqin
 * @date 2018/6/13
 */
public class FrmAddPlan extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelName = new JLabel("名称：");
    private JLabel labelDeadline = new JLabel("截止时间：");
    private JTextField edtName = new JTextField(20);
    private JTextField edtDeadline = new JTextField(20);
    public FrmAddPlan(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelDeadline);
        workPane.add(edtDeadline);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(280, 180);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        } else if (e.getSource() == this.btnOk) {
            String name = this.edtName.getText();
            String deadline = this.edtDeadline.getText();
            try {
                PersonPlanUtil.planManager.addPlan(User.currentLoginUser.getUserId(),name,deadline);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}

