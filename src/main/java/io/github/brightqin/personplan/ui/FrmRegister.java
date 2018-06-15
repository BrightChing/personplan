package io.github.brightqin.personplan.ui;

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
public class FrmRegister extends JDialog implements ActionListener {
    private JButton btnOk = new JButton("注册");
    private JButton btnCancel = new JButton("取消");
    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPasscode = new JPasswordField(20);
    private JPasswordField edtConfirmPasscode = new JPasswordField(20);
    private JComboBox<String> cmbMaxLoginNum;

    public FrmRegister(Dialog f, String s, boolean b) {
        super(f, s, b);
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        JPanel workPane = new JPanel();
        JLabel labelUser = new JLabel("用户：");
        workPane.add(labelUser);
        workPane.add(edtUserId);
        JLabel labelPasscode = new JLabel("密码：");
        workPane.add(labelPasscode);
        workPane.add(edtPasscode);
        JLabel labelConfirmPasscode = new JLabel("密码：");
        workPane.add(labelConfirmPasscode);
        workPane.add(edtConfirmPasscode);
        JLabel labelMaxLoginNum = new JLabel("最大同时登陆数：");
        workPane.add(labelMaxLoginNum);
        String[] maxLogNum = {"", "1", "2", "3", "4", "5"};
        cmbMaxLoginNum = new JComboBox<>(maxLogNum);
        workPane.add(cmbMaxLoginNum);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 180);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
        } else if (e.getSource() == this.btnOk) {
            if (this.cmbMaxLoginNum.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(null, "请选择最大同时登陆数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String userId = this.edtUserId.getText();
            String passcode = new String(this.edtPasscode.getPassword());
            String confirmPasscode = new String(this.edtConfirmPasscode.getPassword());
            try {
                PersonPlanUtil.userManager.register(userId, passcode, confirmPasscode, this.cmbMaxLoginNum.getSelectedIndex());
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

