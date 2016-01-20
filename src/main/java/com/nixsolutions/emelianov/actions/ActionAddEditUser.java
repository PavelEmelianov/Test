package com.nixsolutions.emelianov.actions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.nixsolutions.emelianov.entity.User;
import com.nixsolutions.emelianov.utils.DateParser;
import com.opensymphony.xwork2.Action;

public class ActionAddEditUser extends AbstractAction {

    private static final long serialVersionUID = 1284586918465716003L;

    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String role;
    private String operation;
    private List<User> userList;

    public void validate() {

        Pattern emailPattern = Pattern.compile("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
        Matcher emailMatcher = emailPattern.matcher(email);

        Pattern datePattern = Pattern
                .compile("^\\d{4}-((0\\d)|(1[012]))-(([012]\\d)|3[01])$");
        Matcher dateMatcher = datePattern.matcher(birthday);

        if (login == null || (login.trim().equals(""))) {
            addFieldError("login", "Login cannot be empty");
        }

        if (operation.equals("add")) {
            if (userService.findByLogin(login) != null) {
                addFieldError("login", "User is already exist");
            }
        }

        if (password == null || (password.trim().equals(""))) {
            addFieldError("password", "Password cannot be empty");
        }
        if (confirmPassword == null || (confirmPassword.trim().equals(""))) {
            addFieldError("confirmPassword", "Confirm your password");
        } else if (!password.equals(confirmPassword)) {
            addFieldError("password", "Password mismatch");
        }
        if (email == null || (email.trim().equals(""))) {
            addFieldError("email", "Email cannot be empty");
        } else if (!emailMatcher.find()) {
            addFieldError("email", "Invalid e-mail");
        }

        if (operation.equals("add")) {
            if (userService.findByEmail(email) != null) {
                addFieldError("email", "E-mail is already exist");
            }
        } else {
            if (userService.findByEmail(email) != null && !userService
                    .findByLogin(login).getEmail().equals(email)) {
                addFieldError("email", "E-mail is already exist");
            }
        }

        if (firstName == null || (firstName.trim().equals(""))) {
            addFieldError("firstName", "First name cannot be empty");
        }
        if (lastName == null || (lastName.trim().equals(""))) {
            addFieldError("lastName", "Last name cannot be empty");
        }
        if (birthday == null || (birthday.trim().equals(""))) {
            addFieldError("birthday", "Birthday cannot be empty");
        } else if (!dateMatcher.find()) {
            addFieldError("birthday", "<yyyy-dd-yy> format required");
        }

    }

    public String execute() {

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(DateParser.parseDate(birthday));
        user.setRole(roleService.findByName(role));

        switch (operation) {
        case "add":
            userService.create(user);
            userList = userService.findAll();
            return Action.SUCCESS;
        case "edit":
            user.setId(userService.findByLogin(login).getId());
            userService.update(user);
            userList = userService.findAll();
            return Action.SUCCESS;
        default:
            return Action.INPUT;
        }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
