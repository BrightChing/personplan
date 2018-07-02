package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.Plan;
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
public class FrmSetQuestion extends JDialog implements ActionListener {

    private Plan plan = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelQuestion = new JLabel("密保问题：");
    private JLabel labelAnswer = new JLabel("密保答案：");

    private JTextField edtQuestion = new JTextField(50);
    private JTextField edtAnswer = new JTextField(50);

    public FrmSetQuestion(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelQuestion);
        workPane.add(edtQuestion);
        workPane.add(labelAnswer);
        workPane.add(edtAnswer);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(680, 150);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.dispose();
            return;
        }
        String question = this.edtQuestion.getText();

        String answer = this.edtAnswer.getText();
        User user = User.currentLoginUser;
        try {
            PersonPlanUtil.userManager.setPasscodeQuestion(user, question, answer);
            this.setVisible(false);
        } catch (BaseException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
