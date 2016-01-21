package com.emelianov.actions;

import java.util.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.emelianov.entity.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ActionLogin extends AbstractAction {

    private static final long serialVersionUID = -7447344185138021626L;

    private String login;
    private String password;
    private List<User> userList;

    public String execute() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userService.findByLogin(login);
        ActionContext.getContext().getSession().put("loggedUserFirstName",
                user.getFirstName());
        ActionContext.getContext().getSession().put("loggedUserLastName",
                user.getLastName());

        userList = userService.findAll();
        return Action.SUCCESS;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
