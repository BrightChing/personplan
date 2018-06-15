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
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuPlan = new JMenu("计划管理");
    private JMenu menuStep = new JMenu("步骤管理");
    private JMenu menu_static = new JMenu("查询统计");
    private JMenu menu_more = new JMenu("更多");

    private JMenuItem menuItem_AddPlan = new JMenuItem("新建计划");
    private JMenuItem menuItem_DeletePlan = new JMenuItem("删除计划");
    private JMenuItem menuItem_ModifyPlan = new JMenuItem("修改计划");
    private JMenuItem menuItem_AddStep = new JMenuItem("添加步骤");
    private JMenuItem menuItem_DeleteStep = new JMenuItem("删除步骤");
    private JMenuItem menuItem_ModifyStep = new JMenuItem("修改步骤");
    private JMenuItem menuItem_startStep = new JMenuItem("开始步骤");
    private JMenuItem menuItem_finishStep = new JMenuItem("结束步骤");

    private JMenuItem menuItem_static1 = new JMenuItem("超时完成计划统计");
    private JMenuItem menuItem_static2 = new JMenuItem("按时完成计划统计");

    private JMenuItem menuItem_modifyPwd = new JMenuItem("密码修改");
    private JMenuItem menuItem_setpwdques = new JMenuItem("设置密保");
    private JMenuItem menuItem_modifyPwdques = new JMenuItem("修改密保");
    private FrmLogin dlgLogin = null;
    private JPanel statusBar = new JPanel();

    private Object[] tblPlanTitle = Plan.TABLE_TITLES;
    private Object[][] tblPlanData;
    DefaultTableModel tabPlanModel = new DefaultTableModel();
    private JTable dataTablePlan = new JTable(tabPlanModel);

    private Object[] tblStepTitle = Step.TBL_STEP_TITLE;
    private Object[][] tblStepData;
    DefaultTableModel tabStepModel = new DefaultTableModel();
    private JTable dataTableStep = new JTable(tabStepModel);

    private Plan curPlan = null;
    List<Plan> allPlan = null;
    List<Step> planSteps = null;

    void reloadPlanTable() {// 这是测试数据，需要用实际数替换
        try {
            allPlan = PersonPlanUtil.planManager.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblPlanData = new Object[allPlan.size()][Plan.TABLE_TITLES.length];
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

    private void reloadPlanStepTable(int planId) {
        if (planId < 0) {
            return;
        }
        curPlan = allPlan.get(planId);
        try {
            planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblStepData = new Object[planSteps.size()][Step.TBL_STEP_TITLE.length];
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

    private void reloadPlanStepTable(Plan curPlan) {
        try {
            planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblStepData = new Object[planSteps.size()][Step.TBL_STEP_TITLE.length];
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

    public FrmMain() {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("个人计划管理系统");
        dlgLogin = new FrmLogin(this, "登陆", true);
        dlgLogin.setVisible(true);
        // 菜单
        this.menuPlan.add(this.menuItem_AddPlan);
        this.menuItem_AddPlan.addActionListener(this);
        this.menuPlan.add(this.menuItem_DeletePlan);
        this.menuItem_DeletePlan.addActionListener(this);
        this.menuPlan.add(this.menuItem_ModifyPlan);
        this.menuItem_ModifyPlan.addActionListener(this);
        this.menuStep.add(this.menuItem_AddStep);
        this.menuItem_AddStep.addActionListener(this);
        this.menuStep.add(this.menuItem_DeleteStep);
        this.menuItem_DeleteStep.addActionListener(this);
        this.menuStep.add(this.menuItem_ModifyStep);
        this.menuItem_ModifyStep.addActionListener(this);
        this.menuStep.add(this.menuItem_startStep);
        this.menuItem_startStep.addActionListener(this);
        this.menuStep.add(this.menuItem_finishStep);
        this.menuItem_finishStep.addActionListener(this);

        this.menu_static.add(this.menuItem_static1);
        this.menuItem_static1.addActionListener(this);
        this.menu_static.add(this.menuItem_static2);
        this.menuItem_static2.addActionListener(this);

        this.menu_more.add(this.menuItem_modifyPwd);
        this.menuItem_modifyPwd.addActionListener(this);
        this.menu_more.add(this.menuItem_setpwdques);
        this.menuItem_setpwdques.addActionListener(this);
        this.menu_more.add(this.menuItem_modifyPwdques);
        this.menuItem_modifyPwdques.addActionListener(this);

        menuBar.add(menuPlan);
        menuBar.add(menuStep);
        menuBar.add(menu_static);
        menuBar.add(menu_more);
        this.setJMenuBar(menuBar);

        this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
        this.dataTablePlan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmMain.this.dataTablePlan.getSelectedRow();
                if (i < 0) {
                    return;
                }
                FrmMain.this.reloadPlanStepTable(i);
            }

        });
        this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);

        this.reloadPlanTable();
        // 状态栏
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuItem_AddPlan) {
            FrmAddPlan dlg = new FrmAddPlan(this, "添加计划", true);
            dlg.setVisible(true);
            this.reloadPlanTable();
        }
//        else if (e.getSource() == this.menuItem_DeletePlan) {
//            if (this.curPlan == null) {
//                JOptionPane.showMessageDialog(null, "请选择计划", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            try {
//                PersonPlanUtil.planManager.deletePlan(this.curPlan);
//                this.reloadPlanTable();
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            }
//        }
// else if (e.getSource() == this.menuItem_ModifyPlan) {
//            if (this.curPlan == null) {
//                JOptionPane.showMessageDialog(null, "请选择计划", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            FrmModifyPlan dlg = new FrmModifyPlan(this, "修改计划名", true);
//            dlg.plan = curPlan;
//            dlg.setVisible(true);
//            this.reloadPlanTable();
//        } else if (e.getSource() == this.menuItem_AddStep) {
//            FrmAddStep dlg = new FrmAddStep(this, "添加步骤", true);
//            dlg.plan = curPlan;
//            dlg.setVisible(true);
//            this.reloadPlanStepTabel(this.curPlan);
//
//            this.reloadPlanTable();
//        } else if (e.getSource() == this.menuItem_DeleteStep) {
//            int i = FrmMain.this.dataTableStep.getSelectedRow();
//            if (i < 0) {
//                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            try {
//                PersonPlanUtil.stepManager.deleteStep(this.planSteps.get(i));
//                this.reloadPlanTable();
//                this.reloadPlanStepTabel(this.curPlan);
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        } else if (e.getSource() == this.menuItem_ModifyStep) {
//            int i = FrmMain.this.dataTableStep.getSelectedRow();
//            if (i < 0) {
//                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            FrmModifyStep dlg = new FrmModifyStep(this, "修改步骤", true);
//            dlg.step = this.planSteps.get(i);
//            dlg.setVisible(true);
//            this.reloadPlanTable();
//            this.reloadPlanStepTabel(this.curPlan);
//        } else if (e.getSource() == this.menuItem_startStep) {
//            int i = FrmMain.this.dataTableStep.getSelectedRow();
//            if (i < 0) {
//                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            try {
//                PersonPlanUtil.stepManager.startStep(this.planSteps.get(i));
//                this.reloadPlanStepTabel(this.curPlan);
//                this.reloadPlanTable();
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        } else if (e.getSource() == this.menuItem_finishStep) {
//            int i = FrmMain.this.dataTableStep.getSelectedRow();
//            if (i < 0) {
//                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            try {
//                PersonPlanUtil.stepManager.finishStep(this.planSteps.get(i));
//                this.reloadPlanStepTabel(this.curPlan);
//                this.reloadPlanTable();
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        } else if (e.getSource() == this.menuItem_static1) {
//            FrmStastic1 dlg = new FrmStastic1(this, "超时完成计划统计", true);
//            dlg.setVisible(true);
//        } else if (e.getSource() == this.menuItem_static2) {
//            FrmStastic2 dlg = new FrmStastic2(this, "按时完成计划统计", true);
//            dlg.setVisible(true);
//        } else if (e.getSource() == this.menuItem_modifyPwd) {
//            FrmModifyPwd dlg = new FrmModifyPwd(this, "密码修改", true);
//            dlg.setVisible(true);
//        } else if (e.getSource() == this.menuItem_setpwdques) {
//            if (BeanUser.currentLoginUser.getPwdquestion() != null) {
//                JOptionPane.showMessageDialog(null, "已设置了密保", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            FrmSetpwdquestion dlg = new FrmSetpwdquestion(this, "密保设置", true);
//            dlg.setVisible(true);
//        } else if (e.getSource() == this.menuItem_modifyPwdques) {
//            if (BeanUser.currentLoginUser.getPwdquestion() == null) {
//                JOptionPane.showMessageDialog(null, "还未设置了密保", "错误", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            FrmModifypwdquestion dlg = new FrmModifypwdquestion(this, "修改密保", true);
//            dlg.setVisible(true);
//        }
    }

}
