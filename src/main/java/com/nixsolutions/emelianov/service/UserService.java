package com.nixsolutions.emelianov.service;

import java.util.List;

import javax.inject.*;

import com.nixsolutions.emelianov.dao.RoleDao;
import com.nixsolutions.emelianov.dao.UserDao;
import com.nixsolutions.emelianov.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;

@Named("userService")
public class UserService {

    @Inject
    @Qualifier(value = "userDao")
    private UserDao userDao;
    @Inject
    @Qualifier(value = "roleDao")
    private RoleDao roleDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public UserService() {
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}