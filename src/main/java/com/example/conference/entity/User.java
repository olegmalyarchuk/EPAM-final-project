package com.example.conference.entity;

import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {
    private static final long serialVersionUID = 4862926644813433707L;

    private int id;
    private int role_id;
    private String user_name;
    private String user_surname;
    private String user_password;
    private String user_phone;
    private String user_email;
    private String user_photo_url;
    private String user_address;
    private User_roles user_roles;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    public User(String user_email) {
        this.user_email = user_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_photo_url() {
        return user_photo_url;
    }

    public void setUser_photo_url(String user_photo_url) {
        this.user_photo_url = user_photo_url;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public User_roles getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(User_roles user_roles) {
        this.user_roles = user_roles;
        this.role_id = user_roles.getRole_id();
    }

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }
        public Builder withId(Integer id) {
            newUser.id = id;
            return this;
        }
        public Builder withRoleId(Integer roleId) {
            newUser.role_id = roleId;
            return this;
        }
        public Builder withName(String name) {
            newUser.user_name = name;
            return this;
        }
        public Builder withSurname(String surname) {
            newUser.user_surname = surname;
            return this;
        }
        public Builder withPassword(String password) {
            newUser.user_password = password;
            return this;
        }
        public Builder withEmail(String email) {
            newUser.user_email = email;
            return this;
        }
        public Builder withPhone(String phone) {
            newUser.user_phone = phone;
            return this;
        }
        public Builder withAddress(String address) {
            newUser.user_address = address;
            return this;
        }
        public Builder withPhoto(String photo) {
            newUser.user_photo_url = photo;
            return this;
        }
        public User build() {
            return newUser;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", user_name='" + user_name + '\'' +
                ", user_surname='" + user_surname + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_photo_url='" + user_photo_url + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_roles=" + user_roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_name.equals(user.user_name) && user_surname.equals(user.user_surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_name, user_surname);
    }
}
