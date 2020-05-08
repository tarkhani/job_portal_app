package com.example.jport.model;

public class job {

    private int id;
    private String company;
    private String title;
    private String country;
    private String province;
    private int yearsOfExper;
    private int salary;
    private String phone;
    private String description;
    private int UserID;

    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }

    public String getCompany(){return company; }
    public void setCompany(String company){
        this.company = company;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getUserID(){
        return UserID;
    }

    public void setUserID(int UserID){
        this.UserID = UserID;
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
