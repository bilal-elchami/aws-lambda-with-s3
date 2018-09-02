package example.model;

import java.util.Date;

public class UserDto {

    private String gender;
    private String fname;
    private String lname;
    private String email;
    private String country;
    private int dept = -1;
    private String birth; //YYYY-MM-DD
    private String IDentity;

    public UserDto(String gender, String fname,
                   String lname, String email,
                   String country, int dept,
                   String birth, String IDentity) {
        this.gender = gender;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.country = country;
        this.dept = dept;
        this.birth = birth;
        this.IDentity = IDentity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getIDentity() {
        return IDentity;
    }

    public void setIDentity(String IDentity) {
        this.IDentity = IDentity;
    }

    public void setBirth(Date birthDate) {

    }

    @Override
    public String toString() {
        return "UserDto{" +
                "gender='" + gender + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", dept=" + dept +
                ", birth='" + birth + '\'' +
                ", IDentity='" + IDentity + '\'' +
                '}';
    }

}
