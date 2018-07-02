package io.github.brightqin.personplan.controller;

import io.github.brightqin.personplan.dao.UserDAO;
import io.github.brightqin.personplan.dao.UserDAOImpl;
import io.github.brightqin.personplan.entity.User;
import io.github.brightqin.personplan.util.BaseException;


/**
 * @author brightqin
 * @date 2018/6/13
 */
public class UserManagerImpl implements UserManager {
    private UserDAO userDAO = new UserDAOImpl();

    /**
     * 注册： 要求用户名不能重复，不能为空 两次输入的密码必须一致，密码不能为空 如果注册失败，则抛出异常
     *
     * @param userId          用户ID
     * @param passcode        密码
     * @param confirmPasscode 确认密码
     * @param maxLogNum       最大同时登录数
     * @throws BaseException 异常
     */
    @Override
    public void register(String userId, String passcode, String confirmPasscode, int maxLogNum) throws BaseException {
        if (!passcode.equals(confirmPasscode)) {
            throw new BaseException("密码与确认密码不一致");
        }
        if (userDAO.getUser(userId) != null) {
            throw new BaseException("用户已存在");
        }
        userDAO.saveUser(new User(userId, passcode, maxLogNum));
    }

    /**
     * 登陆 1、如果用户不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
     *
     * @param userId   用户ID
     * @param passcode 密码
     * @return user 用户对象
     * @throws BaseException 异常
     */
    @Override
    public User login(String userId, String passcode) throws BaseException {
        if ("".equals(userId)) {
            throw new BaseException("用户名不能为空");
        }
        if ("".equals(passcode)) {
            throw new BaseException("密码不能为空");
        }
        User user = userDAO.getUser(userId);
        if (user == null) {
            throw new BaseException("用户不存在");
        } else if (!passcode.equals(user.getPasscode())) {
            throw new BaseException("密码错误");
        }
        return user;
    }

    /**
     * 修改密码
     *
     * @param user            当前用户
     * @param oldPassword     原密码
     * @param newPassword     新密码
     * @param confirmPasscode 重复输入的新密码
     * @throws BaseException 异常
     */
    @Override
    public void changePasscode(User user, String oldPassword, String newPassword, String confirmPasscode) throws BaseException {
        if (!user.getPasscode().equals(oldPassword)) {
            throw new BaseException("原密码错误");
        }
        if (!newPassword.equals(confirmPasscode)) {
            throw new BaseException("密码与确认密码不一致");
        }
        user.setPasscode(newPassword);
        userDAO.updateUser(user);
    }

    /**
     * 退出
     *
     * @param user 用户对象
     */
    @Override
    public void logout(User user) {

    }

    /**
     * 设置密保问题
     *
     * @param user     用户对象
     * @param question 密保问题
     * @param answer   密保答案
     */
    @Override
    public void setPasscodeQuestion(User user, String question, String answer) {
        user.setQuestion(question);
        user.setAnswer(answer);
        userDAO.updateUser(user);
        User.currentLoginUser = user;
    }

    /**
     * 密保问题通过修改密码
     *
     * @param user            用户对象
     * @param answer          密保问题的答案
     * @param passcode        密码
     * @param confirmPasscode 确认密码
     * @throws BaseException 异常
     */
    @Override
    public void changePasscodeByQuestion(User user, String answer, String passcode, String confirmPasscode) throws BaseException {
        if (user.getQuestion() == null) {
            throw new BaseException("还未设置密保问题");
        } else if (!answer.equals(user.getAnswer())) {
            throw new BaseException("您输入的答案有误");
        }
        if (!confirmPasscode.equals(passcode)) {
            throw new BaseException("密码与确认密码不一致");
        }
        user.setPasscode(passcode);
        userDAO.updateUser(user);
        User.currentLoginUser = user;
    }

    /**
     * 修改密保问题或答案
     *
     * @param user      用户对象
     * @param question  密保问题
     * @param answer    密保答案
     * @param oldAnswer 老的密保答案
     * @throws BaseException 异常
     */
    @Override
    public void changePasscodeQuestion(User user, String question, String answer, String oldAnswer) throws BaseException {
        if (!user.getAnswer().equals(oldAnswer)) {
            throw new BaseException("原密保问题答案不正确");
        }
        user.setQuestion(question);
        user.setAnswer(answer);
        userDAO.updateUser(user);
        User.currentLoginUser = user;
    }

    @Override
    public User getUser(String userId) {
        return userDAO.getUser(userId);
    }
}
