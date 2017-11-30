package com.qssoft.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAccessHelper
{
    private static Authentication getAuthentication()
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return securityContext.getAuthentication();
    }

    public static int getUserId()
    {
        Authentication authentication = getAuthentication();
        CustomUser user = null;
        if(null != authentication){
            user = (CustomUser) authentication.getPrincipal();
        }
        return user.getId();
    }

    private static boolean checkUserRole(final String roleName)
    {
        Authentication authentication = getAuthentication();

        return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(roleName));
    }

    public static boolean isOwner() {
        return checkUserRole("ROLE_OWNER");
    }

    public static boolean isAdmin() {
        return checkUserRole("ROLE_ADMIN");
    }

    public static boolean isBuyer() {
        return checkUserRole("ROLE_BUYER");
    }

    public static boolean isAnonymous() {
        return checkUserRole("ROLE_ANONYMOUS");
    }
}
