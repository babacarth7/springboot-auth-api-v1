package com.babacarthiam.springboot_auth_api_v1.dtos;

public class RegisterUserDto {
    private String fullName;
    private String email;
    private String password;

    public String getFullName() {
        return fullName;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" + "fullName='" + fullName + '\'' + ", email='"
                + email + '\'' + ", password='" + password + '\'' + '}';
    }
}
