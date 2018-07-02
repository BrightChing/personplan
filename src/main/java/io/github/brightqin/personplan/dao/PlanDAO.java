package io.github.brightqin.personplan.dao;

import io.github.brightqin.personplan.entity.Plan;

import java.util.List;

/**
 * 计划的数据库操作
 *
 * @author brightqin
 * @date 2018/6/13
 */
public interface PlanDAO {

    /**
     * 保存计划
     *
     * @param plan 计划对象
     */
    void savePlan(Plan plan);

    /**
     * 通过用户ID查找计划
     *
     * @param userId 用户ID
     * @return List<Plan
                    */
    List<Plan> listAllByUserId(String userId);

    /**
     * 删除计划
     *
     * @param plan 计划对象
     */
    void deletePlan(Plan plan);

    /**
     * 更新计划
     *
     * @param plan 计划对象
     */
    void updatePlan(Plan plan);
}
