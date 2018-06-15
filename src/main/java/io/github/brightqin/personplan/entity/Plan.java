package io.github.brightqin.personplan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author brightqin
 * @date 2018/6/12
 */
@Entity
public class Plan {
    public static final String[] TABLE_TITLES = {"编号", "名称", "步骤数", "完成数", "已开始未完成数", "截至时间"};

    /**
     * ID
     */
    private Long planId;
    /**
     * 所属用户ID
     */
    private String userId;
    /**
     * 计划名
     */
    private String planName;
    /**
     * 步骤数
     */
    private int stepNum;
    /**
     * 已完成数
     */
    private int finishNum;
    /**
     * 已开始未完成数
     */
    private int starFinishNum;
    /**
     * 计划创建时间
     */
    private Date planCreateTime;
    /**
     * 截止时间
     */
    private Date deadLine;

    public Plan(String userId, String planName, Date planCreateTime, Date deadLine) {
        this.userId = userId;
        this.planName = planName;
        this.planCreateTime = planCreateTime;
        this.deadLine = deadLine;
    }

    public Plan() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getStarFinishNum() {
        return starFinishNum;
    }

    public void setStarFinishNum(int starFinishNum) {
        this.starFinishNum = starFinishNum;
    }

    public Date getPlanCreateTime() {
        return planCreateTime;
    }

    public void setPlanCreateTime(Date planCreateTime) {
        this.planCreateTime = planCreateTime;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getCell(int col) {
        switch (col) {
            case 0:
                return String.valueOf(this.getPlanId());
            case 1:
                return this.getPlanName();
            case 2:
                return String.valueOf(this.getStepNum());
            case 3:
                return String.valueOf(this.getFinishNum());
            case 4:
                return String.valueOf(this.getStarFinishNum());
            case 5:
                return String.valueOf(this.getDeadLine());
            default:
                return "";
        }
    }
}
