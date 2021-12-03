package com.example.assignmentfirebase;

public class Children {
    private String name;
    private String age;
    private String dob;

    public Children(String name, String age, String dob) {
        this.name = name;
        this.age = age;
        this.dob = dob;
    }

    public Children(String name, int age, int dob) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
