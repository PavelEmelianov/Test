package com.nixsolutions.emelianov.tag;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.nixsolutions.emelianov.entity.User;

public class CustomTag extends SimpleTagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws IOException {
    
        String login = user.getLogin();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(
                System.currentTimeMillis() - user.getBirthday().getTime());
        int age = c.get(Calendar.YEAR) - 1970;
        String roleName = user.getRole().getName();

        getJspContext().getOut()
                .print("<td>" + login + "</td><td>" + firstName + "</td><td>"
                        + lastName + "</td><td>" + age + "</td><td>" + roleName
                        + "</td>");

    }

}
