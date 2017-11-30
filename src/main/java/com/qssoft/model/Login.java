package com.qssoft.model;

public class Login
{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;

        Login login = (Login) o;

        if (getUsername() != null ? !getUsername().equals(login.getUsername()) : login.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(login.getPassword()) : login.getPassword() == null;

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }
}
