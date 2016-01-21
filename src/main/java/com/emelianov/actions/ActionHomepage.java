package com.emelianov.actions;

import java.util.List;
import com.emelianov.entity.User;

public class ActionHomepage extends AbstractAction {

    private static final long serialVersionUID = 60159237172450388L;

    private List<User> userList;  

    public String execute() {

        userList = userService.findAll();
        return SUCCESS;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
}
