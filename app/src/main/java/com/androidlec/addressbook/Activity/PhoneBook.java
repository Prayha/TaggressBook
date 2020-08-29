package com.androidlec.addressbook.Activity;

public class PhoneBook {

    private String id;
    private String name;
    private String tel;
    private String email;
    private String group;

    public PhoneBook(String id, String name, String tel, String email, String group) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.group = group;
    }

    public PhoneBook() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
