package io.github.brightqin.personplan.controller;

import java.util.List;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;
import io.github.brightqin.personplan.util.BaseException;

/**
 * @author brightqin
 */
public interface StepManager {

    /**
     * 添加步骤
     *
     * @throws BaseException 异常
     */
    void addStep() throws BaseException;

    /**
     * 加载步骤
     *
     * @param plan 计划
     * @return List<Step>
     * @throws BaseException 异常
     */
    List<Step> loadSteps(Plan plan) throws BaseException;

    /**
     * 删除步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    void deleteStep(Step step) throws BaseException;


    /**
     * 开始一个步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    void startStep(Step step) throws BaseException;

    /**
     * 完成一个步骤
     *
     * @param step 步骤对象
     * @throws BaseException 异常
     */
    void finishStep(Step step) throws BaseException;

    /**
     * 修改步骤
     *
     * @throws BaseException 异常
     */
    void modifyStep(Step step) throws BaseException;


}
