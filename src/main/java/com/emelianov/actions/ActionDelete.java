package com.emelianov.actions;

import java.util.List;
import com.emelianov.entity.User;
import com.opensymphony.xwork2.Action;;

public class ActionDelete extends AbstractAction {

    private static final long serialVersionUID = -4820420381484413513L;

    private String login;
    private List<User> userList;

    public String execute() {
        userService.remove(userService.findByLogin(login));
        userList = userService.findAll();
        return Action.SUCCESS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
