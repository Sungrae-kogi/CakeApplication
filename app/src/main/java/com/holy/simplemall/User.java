package com.holy.simplemall;

public class User {

    private final String id;
    private final String password;
    private final String name;
    private final String phone;
    private final String address;

    public User(String id, String password, String name, String phone, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
