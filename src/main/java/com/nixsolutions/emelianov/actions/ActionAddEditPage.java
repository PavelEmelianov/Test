package com.nixsolutions.emelianov.actions;
import com.nixsolutions.emelianov.entity.User;
import com.opensymphony.xwork2.Action;

public class ActionAddEditPage extends AbstractAction {

    private static final long serialVersionUID = -1705990126272552918L;

    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String operation;
    private String role;

    public String execute() {
        if (login == null) {
            setOperation("add");
            return Action.SUCCESS;
        }

        User user = userService.findByLogin(login);
        setPassword(user.getPassword());
        setConfirmPassword(user.getPassword());
        setEmail(user.getEmail());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setBirthday(user.getBirthday().toString());
        setOperation("edit");
        setRole(user.getRole().getName());

        return Action.SUCCESS;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
