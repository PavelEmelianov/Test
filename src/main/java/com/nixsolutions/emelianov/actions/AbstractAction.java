package com.nixsolutions.emelianov.actions;
//abstract action
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import com.nixsolutions.emelianov.service.RoleService;
import com.nixsolutions.emelianov.service.UserService;
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
