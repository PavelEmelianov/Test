package com.nixsolutions.emelianov.dao;

import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.nixsolutions.emelianov.entity.Role;

@Named("roleDao")
public class HibernateRoleDao implements RoleDao {

    Logger logger = LoggerFactory.getLogger(HibernateRoleDao.class);

    private static final String FIND_BY_NAME_QUERY = "FROM Role where name = :name";

    @Inject
    private SessionFactory sessionFactory;

    @Transactional
    public void create(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Transactional
    public void update(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    @Transactional
    public void remove(Role role) {
        sessionFactory.getCurrentSession().delete(role);

    }

    @Transactional
    public Role findByName(String name) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_NAME_QUERY);
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
