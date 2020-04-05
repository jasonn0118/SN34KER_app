package com.example.sn34ker.datamodels;

import android.graphics.Bitmap;

public class UserModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String street2;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String DateOfBirth;
    private Bitmap user_profile_image;

    public UserModel(String userId, String firstName, String lastName, String gender, String email,
                     String street2, String street, String city, String province, String postalCode,
                     String dateOfBirth, Bitmap user_profile_image) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.street2 = street2;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        DateOfBirth = dateOfBirth;
        this.user_profile_image = user_profile_image;
    }

    public UserModel(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public Bitmap getUser_profile_image() {
        return user_profile_image;
    }

    public void setUser_profile_image(Bitmap user_profile_image) {
        this.user_profile_image = user_profile_image;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", street2='" + street2 + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                ", user_profile_image=" + user_profile_image +
                '}';
    }
}
