//package io.github.brightqin.personplan.ui;
//
//import io.github.brightqin.personplan.entity.Plan;
//import io.github.brightqin.personplan.util.BaseException;
//import io.github.brightqin.personplan.util.PersonPlanUtil;
//
//import java.awt.BorderLayout;
//import java.awt.Frame;
//import java.awt.Toolkit;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.List;
//
//
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//
//public class FrmStastic1 extends JDialog {
//
//    DefaultTableModel tableMode = new DefaultTableModel();
//    JTable dataTable = new JTable(tableMode);
//    private Object[] tblPlanTitle = Plan.TABLE_TITLES;
//    private Object[][] tblPlanData;
//    List<Plan> allPlan = null;
//
//    void reloadPlanTable() {
//        try {
//            allPlan = PersonPlanUtil.planManager.loadfinish1();
//        } catch (BaseException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        tblPlanData = new Object[allPlan.size()][Plan.TABLE_TITLES.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            tblPlanData[i][0] = i + 1;
//            for (int j = 1; j < Plan.TABLE_TITLES.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
//        }
//        tableMode.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTable.validate();
//        this.dataTable.repaint();
//    }
//
//    public FrmStastic1(Frame f, String s, boolean b) {
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
//                int i = FrmStastic1.this.dataTable.getSelectedRow();
//                if (i < 0) {
//                    return;
//                }
//                JFrame jf = new JFrame();
//                Plan plan = allPlan.get(i);
//                FrmListStep dlg = new FrmListStep(jf, plan.getPlanName() + "超时完成步骤统计", true, plan);
//                dlg.setVisible(true);
//            }
//        });
//    }
//}