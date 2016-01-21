package com.emelianov.service;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Qualifier;
import com.emelianov.dao.RoleDao;
import com.emelianov.entity.Role;

@Named("roleService")
public class RoleService {
         
    @Inject
    @Qualifier(value = "roleDao")
    private RoleDao roleDao;
    
    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void create(Role role) {
        roleDao.create(role);
    }

    public void update(Role role) {
        roleDao.create(role);
    }

    public void remove(Role role) {
        roleDao.create(role);
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
 
}
