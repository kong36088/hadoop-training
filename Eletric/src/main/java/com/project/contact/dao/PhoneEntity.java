package com.project.contact.dao;

import javax.persistence.*;

/**
 * Created by jwl on 2016/4/29.
 */
@Entity
@Table(name = "phone", schema = "java_contact", catalog = "")
public class PhoneEntity implements Comparable{
    private int id;
    private int userId;
    private String number;
    private String number2;
    private String name;
    private String pinyin;
    private String shengmu;
    private String email;
    private String qq;
    private String workAddress;
    private String homeAddress;
    private String birthday;
    private String homePage;
    private String postCode;
    private String image;
    private String mark;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 255)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "number2", nullable = true, length = 255)
    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pinyin", nullable = false, length = 255)
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Basic
    @Column(name = "shengmu", nullable = false, length = 255)
    public String getShengmu() {
        return shengmu;
    }

    public void setShengmu(String shengmu) {
        this.shengmu = shengmu;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "QQ", nullable = true, length = 255)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "work_address", nullable = true, length = 255)
    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    @Basic
    @Column(name = "home_address", nullable = true, length = 255)
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Basic
    @Column(name = "birthday", nullable = true, length = 255)
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "home_page", nullable = true, length = 255)
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Basic
    @Column(name = "post_code", nullable = true, length = 255)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "mark", nullable = true, length = -1)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntity that = (PhoneEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (number2 != null ? !number2.equals(that.number2) : that.number2 != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pinyin != null ? !pinyin.equals(that.pinyin) : that.pinyin != null) return false;
        if (shengmu != null ? !shengmu.equals(that.shengmu) : that.shengmu != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (workAddress != null ? !workAddress.equals(that.workAddress) : that.workAddress != null) return false;
        if (homeAddress != null ? !homeAddress.equals(that.homeAddress) : that.homeAddress != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        if (postCode != null ? !postCode.equals(that.postCode) : that.postCode != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (number2 != null ? number2.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pinyin != null ? pinyin.hashCode() : 0);
        result = 31 * result + (shengmu != null ? shengmu.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (workAddress != null ? workAddress.hashCode() : 0);
        result = 31 * result + (homeAddress != null ? homeAddress.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", number2='" + number2 + '\'' +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", shengmu='" + shengmu + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", birthday='" + birthday + '\'' +
                ", homePage='" + homePage + '\'' +
                ", postCode='" + postCode + '\'' +
                ", image='" + image + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
    public String toCsv(){
        return number+","+number2+","+name+","+pinyin+","+shengmu+","+email+","+qq+","+workAddress+","+homeAddress+","+birthday+","+homePage+","+postCode+","+image+","+mark+",";
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof PhoneEntity){
            PhoneEntity s=(PhoneEntity)o;
            int c=this.pinyin.compareToIgnoreCase(s.pinyin);
            System.out.println("compare:"+c);
            if(c>0){
                return 1;
            }
            else{
                return 0;
            }
        }
        return -1;
    }

}
