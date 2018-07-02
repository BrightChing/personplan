package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.util.BaseException;

import java.util.List;


/**
 * PlanManager
 *
 * @author brightqin
 */
public interface PlanManager {

    /**
     * 添加计划
     *
     * @param userId         所属用户ID
     * @param planName       计划名
     * @param deadLine       计划截止时间
     * @throws BaseException
     */
    void addPlan(String userId, String planName,String deadLine) throws BaseException;

    /**
     * 提取所有计划
     * @param useId useId
     * @return 加载所有的计划列表
     * @throws BaseException 异常
     */
    List<Plan> loadAllByUserId(String useId) throws BaseException;

    /**
     * 删除计划，如果计划下存在步骤，则不允许删除
     *
     * @param plan 计划
     * @throws BaseException 异常
     */
    void deletePlan(Plan plan) throws BaseException;

    /**
     * 修改计划
     *
     * @param plan 计划
     * @throws BaseException 异常
     */
    void modifyPlan(Plan plan) throws BaseException;

}
