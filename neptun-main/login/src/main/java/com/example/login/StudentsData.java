package com.example.login;

public class StudentsData {

    private String neptun;
    private String first_name;
    private String last_name;
    private String gender;
    private String gmail;
    private Integer missing;
    private String type;
    private Double zh1;
    private Double zh2;
    private Double sum;
    private String status;


    public StudentsData(String neptun, String first_name, String last_name, String gender, String gmail, int missing, String type) {
        this.neptun = neptun;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.gmail = gmail;
        this.missing = missing;
        this.type = type;
    }

    public StudentsData(String neptun, Double zh1, Double zh2, Double sum, String status) {
        this.neptun = neptun;
        this.zh1 = zh1;
        this.zh2 = zh2;
        this.sum = sum;
        this.status = status;


    }

    public String getNeptun() {
        return neptun;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getGmail() {
        return gmail;
    }

    public int getMissing() {
        return missing;
    }

    public String getType() {
        return type;
    }

    public Double getZh1() {
        return zh1;
    }

    public Double getZh2() {
        return zh2;
    }

    public Double getSum() {
        return sum = zh1+zh2;
    }

    public String getStatus() {

        if (sum>40){
            status ="pass";
        }else {
            status="faild";
        }
        return status;
    }
}
