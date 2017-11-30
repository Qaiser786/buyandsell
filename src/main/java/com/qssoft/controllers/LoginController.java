package com.qssoft.controllers;

import com.qssoft.dto.UserDTO;
import com.qssoft.services.RealEstateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController
{
    @Autowired
    RealEstateUserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String doLogin() {
        return "login/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String loginProcessing() {
        return "login/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String doLogout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String doRegister (HttpServletRequest request, HttpServletResponse response, Model model) {
        UserDTO user = new UserDTO();

        model.addAttribute("user", user);

        return "login/register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String performRegistration (@ModelAttribute UserDTO user, HttpServletResponse response) {

        userDetailsService.registerNewUser(user);

        return "redirect:/login";
    }

}
