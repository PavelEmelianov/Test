package com.nixsolutions.emelianov.dao;

import java.util.*;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.emelianov.entity.User;

@Named("userDao")
public class HibernateUserDao implements UserDao {

    Logger logger = LoggerFactory.getLogger(HibernateUserDao.class);

    private static final String FIND_BY_LOGIN_QUERY = "FROM User where login = :login";
    private static final String FIND_BY_EMAIL_QUERY = "FROM User where email = :email";
    private static final String FIND_ALL_QUERY = "FROM User";

    @Inject
    private SessionFactory sessionFactory;
    @Inject
    private RoleDao roleDao;

    @Transactional
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    public void remove(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> findAll() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_ALL_QUERY);
        return query.list();
    }

    @Transactional
    public User findByLogin(String login) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_LOGIN_QUERY);
        query.setParameter("login", login);
        return (User) query.uniqueResult();
    }

    @Transactional
    public User findByEmail(String email) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_EMAIL_QUERY);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }
    
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
