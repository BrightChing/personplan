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
public class FrmForgetPasscode extends JDialog implements ActionListener {
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JTextField edtAnswer = new JTextField(50);
    private JPasswordField edtPasscode = new JPasswordField(20);
    private JPasswordField edtConfirmPasscode = new JPasswordField(20);
    public FrmForgetPasscode(Dialog f, String s, boolean b) {
        super(f, s, b);
        JLabel labelQuestion = new JLabel("");
        labelQuestion.setText("密保问题："+ User.currentLoginUser.getQuestion());
        JPanel toolBar1 = new JPanel();
        toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar1.add(labelQuestion);
        this.getContentPane().add(toolBar1, BorderLayout.NORTH);
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        JPanel workPane = new JPanel();
        JLabel labelAnswer = new JLabel("密保答案：");
        workPane.add(labelAnswer);
        workPane.add(edtAnswer);
        JLabel labelPasscode = new JLabel("密码：");
        workPane.add(labelPasscode);
        workPane.add(edtPasscode);
        JLabel labelConfirmPasscode = new JLabel("密码：");
        workPane.add(labelConfirmPasscode);
        workPane.add(edtConfirmPasscode);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(650, 170);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
        } else if (e.getSource() == this.btnOk) {
            String answer = this.edtAnswer.getText();
            String passcode = new String(this.edtPasscode.getPassword());
            String confirmPasscode = new String(this.edtConfirmPasscode.getPassword());
            try {
                PersonPlanUtil.userManager.changePasscodeByQuestion(User.currentLoginUser, answer, passcode, confirmPasscode);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
