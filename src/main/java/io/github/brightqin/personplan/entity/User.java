package io.github.brightqin.personplan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author brightqin
 * @date 2018/6/12
 */
@Entity
public class User {
    public static User currentLoginUser = null;
    private String userId;
    private String passcode;
    private int maxErrorNum;
    private int maxLoginNum;
    private String question;
    private String answer;

    public User(String userId, String passcode, int maxLoginNum) {
        this.userId = userId;
        this.passcode = passcode;
        this.maxLoginNum = maxLoginNum;
    }

    public User() {

    }



    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    @Column(columnDefinition = "int default 3")
    public int getMaxErrorNum() {
        return maxErrorNum;
    }

    public void setMaxErrorNum(int maxErrorNum) {
        this.maxErrorNum = maxErrorNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMaxLoginNum() {
        return maxLoginNum;
    }

    public void setMaxLoginNum(int maxLoginNum) {
        this.maxLoginNum = maxLoginNum;
    }
}
