package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.Plan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author brightqin
 * @date 2018/6/18
 */
public class FrmAddStep extends JDialog implements ActionListener {
    public Plan plan = null;
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("确定");
    private JButton btnCancel = new JButton("取消");
    private JLabel labelName = new JLabel("计划步骤名称：");
    private JLabel labelPlanStartDate = new JLabel("计划开始日期：");
    private JLabel labelPlanFinishDate = new JLabel("计划完成日期：");

    private JTextField edtPlanStartDate = new JTextField(20);
    private JTextField edtPlanFinishDate = new JTextField(20);
    private JTextField edtName = new JTextField(20);

    public FrmAddStep(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPlanStartDate);
        workPane.add(edtPlanStartDate);
        workPane.add(labelPlanFinishDate);
        workPane.add(edtPlanFinishDate);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(380, 180);
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

        String name = this.edtName.getText();
        String sd = this.edtPlanStartDate.getText();
        String fd = this.edtPlanFinishDate.getText();
        if (plan == null) {
            JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        try {
/////
////                PersonPlanUtil.stepManager.add(plan,name,sd,fd);
//            this.setVisible(false);
//        } catch (BaseException e1) {
//            JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

    }


}
