package com.qssoft.dto;

public class UserDTO
{
    String login;
    String password;
//    Integer roleId;
    String roleName;

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

//    public Integer getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Integer roleId) {
//        this.roleId = roleId;
//    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserDTO() {
    }

//    public UserDTO(String login, String password, Integer roleId) {
//        this.login = login;
//        this.password = password;
//        this.roleId = roleId;
//    }

    public UserDTO(String login, String password, String roleName) {
        this.login = login;
        this.password = password;
        this.roleName = roleName;
    }
}
