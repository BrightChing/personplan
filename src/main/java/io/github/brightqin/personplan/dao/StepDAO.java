package io.github.brightqin.personplan.dao;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;

import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/18
 */
public interface StepDAO {
    /**
     * 添加步骤
     *
     * @param step b
     */
    void addStep(Step step);

    /**
     * 加载步骤
     *
     * @param plan 计划
     * @return List<Step>
     */
    List loadSteps(Plan plan);

    /**
     * 删除步骤
     *
     * @param step 步骤对象
     */
    void deleteStep(Step step);


    /**
     * 修改步骤
     *
     * @param step 步骤对象
     */
    void updateStep(Step step);

}
