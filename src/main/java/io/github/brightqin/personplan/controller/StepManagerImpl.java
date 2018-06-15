package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;
import io.github.brightqin.personplan.util.BaseException;

import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/13
 */
public class StepManagerImpl implements StepManager {
    /**
     * 添加步骤
     *
     * @throws BaseException 异常
     */
    @Override
    public void addStep() throws BaseException {

    }

    /**
     * 加载步骤
     *
     * @param plan 计划
     * @return List<Step>
     * @throws BaseException 异常
     */
    @Override
    public List<Step> loadSteps(Plan plan) throws BaseException {
        return null;
    }

    /**
     * 删除步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    @Override
    public void deleteStep(Step step) throws BaseException {

    }

    /**
     * 开始一个步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    @Override
    public void startStep(Step step) throws BaseException {

    }

    /**
     * 完成一个步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    @Override
    public void finishStep(Step step) throws BaseException {

    }

    /**
     * 修改步骤
     *
     * @param step
     * @throws BaseException 异常
     */
    @Override
    public void modifyStep(Step step) throws BaseException {

    }
}
