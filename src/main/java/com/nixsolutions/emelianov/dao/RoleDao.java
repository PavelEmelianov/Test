package com.nixsolutions.emelianov.dao;

import com.nixsolutions.emelianov.entity.Role;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);
    
}
