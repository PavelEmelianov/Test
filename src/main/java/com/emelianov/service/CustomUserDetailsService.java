package com.emelianov.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.emelianov.dao.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    @Qualifier(value = "userDao")
    private UserDao userDao;

    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
  
        com.emelianov.entity.User domainUser = userDao
                .findByLogin(login);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(domainUser.getLogin(), domainUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked,
                getAuthorities((int) (long) domainUser.getRole().getId()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ROLE_ADMIN");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_USER");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(
            List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}