package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.dao.StepDAO;
import io.github.brightqin.personplan.dao.StepDAOImpl;
import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;

import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/13
 */
public class StepManagerImpl implements StepManager {

    StepDAO stepDAO = new StepDAOImpl();
    /**
     * 添加步骤
     *
     */
    @Override
    public void addStep() {

    }

    /**
     * 加载步骤
     *
     * @param plan 计划
     * @return List<Step>
     */
    @Override
    public List<Step> loadSteps(Plan plan) {


        return null;
    }

    /**
     * 删除步骤
     *
     * @param step 步骤对象
     */
    @Override
    public void deleteStep(Step step) {

    }

    /**
     * 开始一个步骤
     *
     * @param step 步骤对象
     */
    @Override
    public void startStep(Step step) {

    }

    /**
     * 完成一个步骤
     *
     * @param step 步骤对象
     */
    @Override
    public void finishStep(Step step) {

    }

    /**
     * 修改步骤
     *
     * @param step
     */
    @Override
    public void modifyStep(Step step) {

    }
}
