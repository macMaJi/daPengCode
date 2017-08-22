package com.example.free.mymvpdemo.bean;

import java.util.List;

/**
 * Created by wu on 2017/1/5.
 */

public class ContactsInfo {
    private String name;
    private List<String> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
