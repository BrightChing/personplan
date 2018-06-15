package io.github.brightqin.personplan.controller;

import io.github.brightqin.personUser.dao.UserDAO;
import io.github.brightqin.personplan.entity.User;

import org.junit.Test;


/**
 * @author brightqin
 * @date 2018/6/13
 */
public class UserManagerTest {
   UserDAO userDAO =new UserDAO();
    @Test
    public void register() {
       userDAO.saveUser(new User("1","1",4));
    }
}