package io.github.brightqin.personplan.dao;


import io.github.brightqin.personplan.entity.User;


/**
 * @author brightqin
 * @date 2018/6/13
 */
public interface UserDAO {
    /**
     * 保存user
     *
     * @param user 用户对象
     */
    void saveUser(User user);

    /**
     * 查询用户对象
     *
     * @param userId 用户ID
     * @return user 对象
     */
    User getUser(String userId);

    /**
     * 删除用户对象
     *
     * @param user 用户对象
     */
    void deleteUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户对象
     */
    void updateUser(User user);
}
