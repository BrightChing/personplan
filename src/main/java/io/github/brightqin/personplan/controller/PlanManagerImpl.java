package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.dao.PlanDAO;
import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.util.BaseException;
import io.github.brightqin.personplan.util.DataCheckUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/13
 */

public class PlanManagerImpl implements PlanManager {
    @Resource
    private PlanDAO planDAO;

    /**
     * 添加计划
     * @param userId         所属用户ID
     * @param planName       计划名
     * @param planCreateTime 计划创建的时间
     * @param deadLine       计划截止时间
     */
    @Override
    public void addPlan(String userId, String planName, String deadLine) throws BaseException {
        planDAO.savePlan(new Plan(userId, planName, new Date(), DataCheckUtil.convertDate(deadLine)));
    }

    /**
     * 提取所有计划
     *
     * @return 加载所有的计划列表
     * @throws BaseException 异常
     */
    @Override
    public List<Plan> loadAll() throws BaseException {
        return null;
    }

    /**
     * 删除计划，如果计划下存在步骤，则不允许删除
     *
     * @param plan 计划
     * @throws BaseException 异常
     */
    @Override
    public void deletePlan(Plan plan) throws BaseException {

    }

    /**
     * 修改计划
     *
     * @param plan 计划
     * @throws BaseException 异常
     */
    @Override
    public void modifyPlan(Plan plan) throws BaseException {

    }

}
