package com.example.jport.model;

public class Resume {

    private int id;
    private int UserID;
    private String name;
    private String degree;
    private String major;
    private String country;
    private String province;
    private int yearsOfExper;
    private int salary;
    private String phone;
    private String description;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getUserID(){
        return UserID;
    }

    public void setUserID(int UserID){
        this.UserID = UserID;
    }

    public String getDegree(){
        return degree;
    }

    public void setDegree(String degree){
        this.degree = degree;
    }

    public String getMajor(){
        return major;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getProvince(){
        return province;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public int getYearsOfExper(){
        return yearsOfExper;
    }

    public void setYearsOfExper(int yearsOfExper){
        this.yearsOfExper = yearsOfExper;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
