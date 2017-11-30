package com.qssoft.services;

import com.qssoft.dao.RoleDAO;
import com.qssoft.dao.UserDAO;
import com.qssoft.dto.UserDTO;
import com.qssoft.entities.Role;
import com.qssoft.entities.User;
import com.qssoft.security.CustomUser;
import com.qssoft.security.UserAccessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RealEstateUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserDAO userDao;

    @Autowired
    private RoleDAO roleDAO;

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        UserDetails userDetails = getUserDetails(user, authority);

        return userDetails;
    }

    private UserDetails getUserDetails(User user, GrantedAuthority authority) {
        return new CustomUser(
                    user.getLogin(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    Arrays.asList(authority),
                    user.getId());
    }

    public UserDetails loadUserById(final int id) throws UsernameNotFoundException {
        User user = userDao.getUserById(id);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        UserDetails userDetails = getUserDetails(user, authority);
        return userDetails;
    }

    public void registerNewUser(final UserDTO userDTO) {
        Role role = roleDAO.getRoleByRoleName(userDTO.getRoleName());
        User user = new User(userDTO.getLogin(), userDTO.getPassword(), role);
        userDao.createUser(user);
    }

}
