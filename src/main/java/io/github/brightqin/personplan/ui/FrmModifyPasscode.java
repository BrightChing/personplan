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
 * @date 2018/6/18
 */
public class FrmModifyPasscode extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelOldPasscode = new JLabel("原密码：");
    private JLabel labelPasscode = new JLabel("新密码：");
    private JLabel labelConfirmPasscode = new JLabel("新密码：");
    private JPasswordField edtOldPasscode = new JPasswordField(20);
    private JPasswordField edtPasscode = new JPasswordField(20);
    private JPasswordField edtConfirmPasscode = new JPasswordField(20);

    public FrmModifyPasscode(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelOldPasscode);
        workPane.add(edtOldPasscode);
        workPane.add(labelPasscode);
        workPane.add(edtPasscode);
        workPane.add(labelConfirmPasscode);
        workPane.add(edtConfirmPasscode);
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
        try {
            PersonPlanUtil.userManager.changePasscode(User.currentLoginUser, new String(edtOldPasscode.getPassword()),
                    new String(edtPasscode.getPassword()), new String(edtConfirmPasscode.getPassword()));
            this.setVisible(false);
        } catch (BaseException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}
