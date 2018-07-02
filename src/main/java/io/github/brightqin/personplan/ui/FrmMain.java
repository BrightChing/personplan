package io.github.brightqin.personplan.ui;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;
import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.PersonPlanUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


/**
 * @author brightqin
 * @date 2018/6/13
 */
public class FrmMain extends JFrame implements ActionListener {

    private JMenuItem menuItemAddPlan = new JMenuItem("新建计划");
    private JMenuItem menuItemDeletePlan = new JMenuItem("删除计划");
    private JMenuItem menuItemModifyPlan = new JMenuItem("修改计划");
    private JMenuItem menuItemAddStep = new JMenuItem("添加步骤");
    private JMenuItem menuItemDeleteStep = new JMenuItem("删除步骤");
    private JMenuItem menuItemModifyStep = new JMenuItem("修改步骤");
    private JMenuItem menuItemStartStep = new JMenuItem("开始步骤");
    private JMenuItem menuItemFinishStep = new JMenuItem("结束步骤");

//    private JMenuItem menuItem_static1 = new JMenuItem("超时完成计划统计");
//    private JMenuItem menuItem_static2 = new JMenuItem("按时完成计划统计");

    private JMenuItem menuItemModifyPwd = new JMenuItem("密码修改");
    private JMenuItem menuItemSetQues = new JMenuItem("设置密保");
    private JMenuItem menuItemModifyQues = new JMenuItem("修改密保");

    private Object[] tblPlanTitle = Plan.TABLE_TITLES;
    private DefaultTableModel tabPlanModel = new DefaultTableModel();
    private JTable dataTablePlan = new JTable(tabPlanModel);

    private Object[] tblStepTitle = Step.TBL_STEP_TITLE;
    private DefaultTableModel tabStepModel = new DefaultTableModel();
    private JTable dataTableStep = new JTable(tabStepModel);

    private Plan curPlan = null;
    private List<Plan> allPlan = null;
    private List<Step> planSteps = null;

    public FrmMain() {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("个人计划管理系统");
        FrmLogin dlgLogin = new FrmLogin(this, "登陆", true);
        dlgLogin.setVisible(true);
        // 菜单
        JMenu menuPlan = new JMenu("计划管理");
        menuPlan.add(this.menuItemAddPlan);
        this.menuItemAddPlan.addActionListener(this);
        menuPlan.add(this.menuItemDeletePlan);
        this.menuItemDeletePlan.addActionListener(this);
        menuPlan.add(this.menuItemModifyPlan);
        this.menuItemModifyPlan.addActionListener(this);
        JMenu menuStep = new JMenu("步骤管理");
        menuStep.add(this.menuItemAddStep);
        this.menuItemAddStep.addActionListener(this);
        menuStep.add(this.menuItemDeleteStep);
        this.menuItemDeleteStep.addActionListener(this);
        menuStep.add(this.menuItemModifyStep);
        this.menuItemModifyStep.addActionListener(this);
        menuStep.add(this.menuItemStartStep);
        this.menuItemStartStep.addActionListener(this);
        menuStep.add(this.menuItemFinishStep);
        this.menuItemFinishStep.addActionListener(this);

        JMenu menuStatic = new JMenu("查询统计");
        ///
//        menu_static.add(this.menuItem_static1);
//        this.menuItem_static1.addActionListener(this);
//        menu_static.add(this.menuItem_static2);
//        this.menuItem_static2.addActionListener(this);
        JMenu menuMore = new JMenu("更多");
        menuMore.add(this.menuItemModifyPwd);
        this.menuItemModifyPwd.addActionListener(this);
        menuMore.add(this.menuItemSetQues);
        this.menuItemSetQues.addActionListener(this);
        menuMore.add(this.menuItemModifyQues);
        this.menuItemModifyQues.addActionListener(this);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuPlan);
        menuBar.add(menuStep);
        menuBar.add(menuStatic);
        menuBar.add(menuMore);
        this.setJMenuBar(menuBar);

        this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
        this.dataTablePlan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmMain.this.dataTablePlan.getSelectedRow();
                if (i < 0) {
                    return;
                }
                curPlan = allPlan.get(i);
                FrmMain.this.reloadPlanStepTable(curPlan);
            }
        });
        this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);

        this.reloadPlanTable();
        // 状态栏
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        // 修改成 您好！+登陆用户名
        JLabel label = new JLabel("您好!" + User.currentLoginUser.getUserId());
        statusBar.add(label);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    PersonPlanUtil.userManager.logout(User.currentLoginUser);
                } catch (BaseException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    private void reloadPlanTable() {
        // 这是测试数据，需要用实际数替换
        try {
            allPlan = PersonPlanUtil.planManager.loadAllByUserId(User.currentLoginUser.getUserId());
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Object[][] tblPlanData = new Object[allPlan.size()][Plan.TABLE_TITLES.length];
        for (int i = 0; i < allPlan.size(); i++) {
            tblPlanData[i][0] = i + 1;
            for (int j = 1; j < Plan.TABLE_TITLES.length; j++) {
                tblPlanData[i][j] = allPlan.get(i).getCell(j);
            }
        }
        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
        this.dataTablePlan.validate();
        this.dataTablePlan.repaint();
    }

    private void reloadPlanStepTable(Plan curPlan) {
        try {
            planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Object[][] tblStepData = new Object[planSteps.size()][Step.TBL_STEP_TITLE.length];
        for (int i = 0; i < planSteps.size(); i++) {
            tblStepData[i][0] = i + 1;
            for (int j = 1; j < Step.TBL_STEP_TITLE.length; j++) {
                tblStepData[i][j] = planSteps.get(i).getCell(j);
            }
        }
        tabStepModel.setDataVector(tblStepData, tblStepTitle);
        this.dataTableStep.validate();
        this.dataTableStep.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuItemAddPlan) {
            FrmAddPlan dlg = new FrmAddPlan(this, "添加计划", true);
            dlg.setVisible(true);
            this.reloadPlanTable();
        } else if (e.getSource() == this.menuItemDeletePlan) {
            if (this.curPlan == null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.planManager.deletePlan(this.curPlan);
                this.reloadPlanTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.menuItemModifyPlan) {
            if (this.curPlan == null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyPlan dlg = new FrmModifyPlan(this, "修改计划名", true);
            dlg.plan = curPlan;
            dlg.setVisible(true);
            this.reloadPlanTable();
        } else if (e.getSource() == this.menuItemAddStep) {
            FrmAddStep dlg = new FrmAddStep(this, "添加步骤", true);
            dlg.plan = curPlan;
            dlg.setVisible(true);
            this.reloadPlanStepTable(this.curPlan);

            this.reloadPlanTable();
        } else if (e.getSource() == this.menuItemDeleteStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.deleteStep(this.planSteps.get(i));
                this.reloadPlanTable();
                this.reloadPlanStepTable(this.curPlan);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.menuItemModifyStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyStep dlg = new FrmModifyStep(this, "修改步骤", true);
            dlg.step = this.planSteps.get(i);
            dlg.setVisible(true);
            this.reloadPlanTable();
            this.reloadPlanStepTable(this.curPlan);
        } else if (e.getSource() == this.menuItemStartStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.startStep(this.planSteps.get(i));
                this.reloadPlanStepTable(this.curPlan);
                this.reloadPlanTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == this.menuItemFinishStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.finishStep(this.planSteps.get(i));
                this.reloadPlanStepTable(this.curPlan);
                this.reloadPlanTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
            ///
//        } else if (e.getSource() == this.menuItem_static1) {
//            FrmStastic1 dlg = new FrmStastic1(this, "超时完成计划统计", true);
//            dlg.setVisible(true);
//        } else if (e.getSource() == this.menuItem_static2) {
//            FrmStastic2 dlg = new FrmStastic2(this, "按时完成计划统计", true);
//            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItemModifyPwd) {
            FrmModifyPasscode dlg = new FrmModifyPasscode(this, "密码修改", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItemSetQues) {
            if (User.currentLoginUser.getQuestion() != null) {
                JOptionPane.showMessageDialog(null, "已设置了密保", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmSetQuestion dlg = new FrmSetQuestion(this, "密保设置", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItemModifyQues) {
            if (User.currentLoginUser.getQuestion() == null) {
                JOptionPane.showMessageDialog(null, "还未设置了密保", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmModifyQuestion dlg = new FrmModifyQuestion(this, "修改密保", true);
            dlg.setVisible(true);
        }
    }
}
