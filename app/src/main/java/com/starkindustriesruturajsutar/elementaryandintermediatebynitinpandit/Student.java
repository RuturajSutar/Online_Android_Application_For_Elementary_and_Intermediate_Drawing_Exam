package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

public class Student {


    private String Name;
    private String Mobile;
    private String Standard;

    public Student() {

    }

    public Student(String name, String mobile, String standard) {
        Name = name;
        Mobile = mobile;
        Standard = standard;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }
}
