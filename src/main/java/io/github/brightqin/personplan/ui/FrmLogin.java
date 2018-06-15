package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.PersonPlanUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author brightqin
 * @date 2018/6/13
 */
public class FrmLogin extends JDialog implements ActionListener {

    private JButton btnLogin = new JButton("登陆");
    private JButton btnCancel = new JButton("退出");
    private JButton btnRegister = new JButton("注册");
    private JButton btnForgetPasscode = new JButton("忘记密码");

    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);

    FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnRegister);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        toolBar.add(btnForgetPasscode);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        JPanel workPane = new JPanel();
        JLabel labelUser = new JLabel("用户：");
        workPane.add(labelUser);
        workPane.add(edtUserId);
        JLabel labelPwd = new JLabel("密码：");
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(320, 140);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.btnRegister.addActionListener(this);
        this.btnForgetPasscode.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            String userId = this.edtUserId.getText();
            String pwd = new String(this.edtPwd.getPassword());
            try {
                User.currentLoginUser = PersonPlanUtil.userManager.login(userId, pwd);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.setVisible(false);
        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if (e.getSource() == this.btnRegister) {
            FrmRegister dlg = new FrmRegister(this, "注册", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.btnForgetPasscode) {
            String userId = this.edtUserId.getText();
            User.currentLoginUser = PersonPlanUtil.userManager.getUser(userId);
            if (User.currentLoginUser.getQuestion() == null) {
                JOptionPane.showMessageDialog(null, "您没有设置密保", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmForgetPasscode dlg = new FrmForgetPasscode(this, "找回密码", true);
            dlg.setVisible(true);
        }
    }
}
