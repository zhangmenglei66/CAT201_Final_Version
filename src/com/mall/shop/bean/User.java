package com.mall.shop.bean;

public class User {
    private int id;
    private String username;
    private String password;
    private double balance;
    private String sex;
    private String phone;

    private String email;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getBalance() {
        return balance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }


    public User(int id, String username, String password, double balance, String sex, String phone, String email, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.sex = sex;
        this.phone = phone;

        this.email = email;
        this.nickname = nickname;
    }

    public User(String username, String password, String sex, String phone, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
    }

}
