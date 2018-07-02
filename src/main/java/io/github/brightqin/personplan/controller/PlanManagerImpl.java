package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.dao.PlanDAO;
import io.github.brightqin.personplan.dao.PlanDAOImpl;
import io.github.brightqin.personplan.dao.UserDAO;
import io.github.brightqin.personplan.dao.UserDAOImpl;
import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.DateCheckUtil;

import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/13
 */

public class PlanManagerImpl implements PlanManager {
    private PlanDAO planDAO = new PlanDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    /**
     * 添加计划
     *
     * @param userId   所属用户ID
     * @param planName 计划名
     * @param deadLine 计划截止时间
     */
    @Override
    public void addPlan(String userId, String planName, String deadLine) throws BaseException {
        Plan plan = new Plan();
        User user = userDAO.getUser(userId);
        user.setUserId(userId);
        plan.setPlanName(planName);
        plan.setUser(user);
        plan.setDeadLine(DateCheckUtil.convertDate(deadLine));
        planDAO.savePlan(plan);
    }

    /**
     * 提取所有计划
     *
     * @return 加载所有的计划列表
     */
    @Override
    public List<Plan> loadAllByUserId(String userId) {
        return planDAO.listAllByUserId(userId);
    }

    /**
     * 删除计划，如果计划下存在步骤，则不允许删除
     *
     * @param plan 计划
     */
    @Override
    public void deletePlan(Plan plan) {
        planDAO.deletePlan(plan);
    }

    /**
     * 修改计划
     *
     * @param plan 计划
     */
    @Override
    public void modifyPlan(Plan plan) {

    }

}
