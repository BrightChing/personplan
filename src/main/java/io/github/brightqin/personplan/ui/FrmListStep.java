//package io.github.brightqin.personplan.ui;
//
//import java.awt.BorderLayout;
//import java.awt.Toolkit;
//
//import java.util.List;
//
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//
//import io.github.brightqin.personplan.entity.Plan;
//import io.github.brightqin.personplan.entity.Step;
//import io.github.brightqin.personplan.util.BaseException;
//import io.github.brightqin.personplan.util.PersonPlanUtil;
//
///**
// * @author brightqin
// */
//public class FrmListStep extends JDialog {
//
//    private DefaultTableModel tabStepModel = new DefaultTableModel();
//	private JTable dataTableStep = new JTable(tabStepModel);
//    private Object[] tblStepTitle = Step.TBL_STEP_TITLE;
//    private Object[][] tblStepData;
//	List<Step> planSteps = null;
//	private Plan plan;
//
//	private void reloadPlanStepTable(Plan curPlan, Boolean flag) {
//		try {
//			planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan, flag);
//		} catch (BaseException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tblStepData = new Object[planSteps.size()][Step.TBL_STEP_TITLE.length];
//		for (int i = 0; i < planSteps.size(); i++) {
//			tblStepData[i][0] = i + 1;
//			for (int j = 1; j < Step.TBL_STEP_TITLE.length; j++) {
//				tblStepData[i][j] = planSteps.get(i).getCell(j);
//			}
//		}
//		tabStepModel.setDataVector(tblStepData, tblStepTitle);
//		this.dataTableStep.validate();
//		this.dataTableStep.repaint();
//	}
//
//	private void reloadPlanStepTable(Plan curPlan) {
//		try {
//			planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan);
//		} catch (BaseException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tblStepData = new Object[planSteps.size()][Step.TBL_STEP_TITLE.length];
//		for (int i = 0; i < planSteps.size(); i++) {
//			tblStepData[i][0] = i + 1;
//			for (int j = 1; j < Step.TBL_STEP_TITLE.length; j++) {
//				tblStepData[i][j] = planSteps.get(i).getCell(j);
//			}
//		}
//		tabStepModel.setDataVector(tblStepData, tblStepTitle);
//		this.dataTableStep.validate();
//		this.dataTableStep.repaint();
//	}
//
//	public FrmListStep(JFrame jF, String s, boolean b, Plan plan) {
//		super(jF, s, b);
//		// 提取现有数据
//		this.plan = plan;
//		if (s.endsWith("超时完成步骤统计")) {
//            this.reloadPlanStepTable(this.plan, true);
//        } else if (s.endsWith("按时完成步骤统计")) {
//            this.reloadPlanStepTable(this.plan, false);
//        } else {
//            this.reloadPlanStepTable(this.plan);
//        }
//		this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
//		// 屏幕居中显示
//		this.setSize(800, 600);
//		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
//		this.validate();
//	}
//}