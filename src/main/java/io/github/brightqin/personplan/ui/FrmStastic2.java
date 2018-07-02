//package io.github.brightqin.personplan.ui;
//
//import java.awt.BorderLayout;
//import java.awt.Frame;
//import java.awt.Toolkit;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.List;
//
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import io.github.brightqin.personplan.entity.Plan;
//import io.github.brightqin.personplan.util.BaseException;
//import io.github.brightqin.personplan.util.PersonPlanUtil;
//
///**
// * @author brightqin
// */
//public class FrmStastic2 extends JDialog {
//
//    DefaultTableModel tablmod = new DefaultTableModel();
//    private JTable dataTable = new JTable(tablmod);
//    private Object[] tblPlanTitle = Plan.TABLE_TITLES;
//    private Object[][] tblPlanData;
//    List<Plan> allPlan = null;
//
//    private void reloadPlanTable() {
//        // 这是测试数据，需要用实际数替换
//        try {
//            allPlan = PersonPlanUtil.planManager.loadfinish2();
//        } catch (BaseException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        tblPlanData = new Object[allPlan.size()][Plan.TABLE_TITLES.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            tblPlanData[i][0] = i + 1;
//            for (int j = 1; j < Plan.TABLE_TITLES.length; j++) {
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
//            }
//        }
//        tablmod.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTable.validate();
//        this.dataTable.repaint();
//    }
//
//    public FrmStastic2(Frame f, String s, boolean b) {
//        super(f, s, b);
//        //提取现有数据
//        this.reloadPlanTable();
//        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
//
//        // 屏幕居中显示
//        this.setSize(800, 600);
//        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//        this.setLocation((int) (width - this.getWidth()) / 2,
//                (int) (height - this.getHeight()) / 2);
//        this.validate();
//        this.dataTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int i = FrmStastic2.this.dataTable.getSelectedRow();
//                if (i < 0) {
//                    return;
//                }
//                JFrame JF = new JFrame();
//                Plan plan = allPlan.get(i);
//                FrmListStep dlg = new FrmListStep(JF, plan.getPlanName() + "按时完成计划统计", true, plan);
//                dlg.setVisible(true);
//            }
//        });
//    }
//}