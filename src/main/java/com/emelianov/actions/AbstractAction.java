package com.emelianov.actions;
import javax.inject.Inject;

import com.emelianov.service.RoleService;
import com.emelianov.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport {
   
    private static final long serialVersionUID = 6334419977172506636L;
    
    @Inject
    @Qualifier(value = "userService")
    protected UserService userService;

    @Inject
    @Qualifier(value = "roleService")
    protected RoleService roleService;
    
    public abstract String execute();

}
