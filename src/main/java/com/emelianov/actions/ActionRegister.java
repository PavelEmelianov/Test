package com.emelianov.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.emelianov.entity.User;
import com.emelianov.utils.CaptchaValidator;
import com.emelianov.utils.DateParser;
import com.opensymphony.xwork2.ActionContext;

public class ActionRegister extends AbstractAction {

    private static final long serialVersionUID = -2287296300181726551L;

    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String message;

    public void validate() {

        Pattern emailPattern = Pattern.compile("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
        Matcher emailMatcher = emailPattern.matcher(email);

        Pattern datePattern = Pattern
                .compile("^\\d{4}-((0\\d)|(1[012]))-(([012]\\d)|3[01])$");
        Matcher dateMatcher = datePattern.matcher(birthday);

        if (login == null || (login.trim().equals(""))) {
            addFieldError("login", "Login cannot be empty");
        } else if (userService.findByLogin(login) != null) {
            addFieldError("login", "User is already exist");
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
        } else if(userService.findByEmail(email)!=null){
            addFieldError("email", "E-mail is already exist");
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
            addFieldError("birthday",
                    "<yyyy-dd-yy> format required");
        }

        String captchaResponse = ((String[]) ActionContext.getContext()
                .getParameters().get("g-recaptcha-response"))[0];
        if (!CaptchaValidator.verify(captchaResponse)) {
            addFieldError("login", "Invalid captcha");
        }

    }

    public String execute() {
        
        User user = createUserWithoutRole();
        user.setRole(roleService.findByName("user"));
        userService.create(user);
        setMessage("You have been successfully registered!");
        cleanUserData();
        return SUCCESS;

    }

    private User createUserWithoutRole() {

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(DateParser.parseDate(birthday));

        return user;
    }

    private void cleanUserData() {
        setLogin("");
        setPassword("");
        setConfirmPassword("");
        setEmail("");
        setFirstName("");
        setLastName("");
        setBirthday("");
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
