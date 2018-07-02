package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;

/**
 * @author brightqin
 */
public interface UserManager {

    /**
     * 注册： 要求用户名不能重复，不能为空 两次输入的密码必须一致，密码不能为空 如果注册失败，则抛出异常
     *
     * @param userId          用户ID
     * @param passcode        密码
     * @param confirmPasscode 确认密码
     * @param maxLogNum       最大同时登录数
     * @throws BaseException 异常
     */
    void register(String userId, String passcode, String confirmPasscode, int maxLogNum) throws BaseException;

    /**
     * 登陆 1、如果用户不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
     *
     * @param userId 用户ID
     * @param passcode 密码
     * @return user 用户对象
     * @throws BaseException 异常
     */
    User login(String userId, String passcode) throws BaseException;

    /**
     * 修改密码 如果没有成功修改，则抛出异常
     *
     * @param user            当前用户
     * @param oldPassword     原密码
     * @param newPassword     新密码
     * @param confirmPasscode 重复输入的新密码
     * @throws BaseException 异常
     */
    void changePasscode(User user, String oldPassword, String newPassword, String confirmPasscode) throws BaseException;

    /**
     * 退出
     *
     * @param user 用户对象
     * @throws BaseException 异常
     */
    void logout(User user) throws BaseException;

    /**
     * 设置密保问题
     *
     * @param user 用户对象
     * @param question 密保问题
     * @param answer 密保答案
     * @throws BaseException 异常
     */
    void setPasscodeQuestion(User user, String question, String answer) throws BaseException;

    /**
     * 密保问题通过修改密码
     *
     * @param user 用户对象
     * @param answer 密保问题的答案
     * @param passcode 密码
     * @param confirmPasscode 确认密码
     * @throws BaseException 异常
     */
    void changePasscodeByQuestion(User user, String answer, String passcode, String confirmPasscode) throws BaseException;

    /**
     * 修改密保问题或答案
     * @param user 用户对象
     * @param question 密保问题
     * @param answer 密保答案
     * @param oldAnswer 老的密保答案
     * @throws BaseException 异常
     */
    void changePasscodeQuestion(User user, String question, String answer, String oldAnswer) throws BaseException;

    /**
     * @param userId 用户ID
     * @return 用户对象
     */
    User getUser(String userId);
}
