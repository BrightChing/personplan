package io.github.brightqin.personplan.util;

import io.github.brightqin.personplan.controller.*;

/**
 * @author brightqin
 * @date 2018/6/13
 */
public class PersonPlanUtil {
    /**
     * 需要换成自行设计的实现类
     */
    public static PlanManager planManager = new PlanManagerImpl();
    /**
     * 需要换成自行设计的实现类
     */
    public static StepManager stepManager = new StepManagerImpl();
    /**
     * 需要换成自行设计的实现类
     */
    public static UserManager userManager = new UserManagerImpl();
}
