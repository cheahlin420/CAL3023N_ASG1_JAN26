/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_asg1;

// Member class
public class Member {
    
    private String memberId; //No need setter (unique identifier)
    private String name;
    private String dateOfBirth;
    private int age;
    private String gender;
    private String contactNumber;
    private String address;
    private String membershipLevel;
    private String dateOfJoining;
    private String membershipStatus;
    private String expiryDate;

    
    // no-argumentt constructure
    public Member() {
        this("", "", "", 0, "", "", "", "", "", "", "");
    }
    
    // full constructure
    public Member(String memberId, String name, String dateOfBirth, int age, String gender, String contactNumber, String address, String membershipLevel, String dateOfJoining, String membershipStatus, String expiryDate) {
        this.memberId = memberId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.membershipLevel = membershipLevel;
        this.dateOfJoining = dateOfJoining;
        this.membershipStatus = membershipStatus;
        this.expiryDate = expiryDate;
    }


    // Setter 
        public void setName(String name) {
        this.name = name;
    }
        
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    
    // Getter 
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String toString() {
    return "Member ID: " + memberId +
           "\nName: " + name +
           "\nDate of Birth: " + dateOfBirth +
           "\nAge: " + age +
           "\nGender: " + gender +
           "\nContact Number: " + contactNumber +
           "\nAddress: " + address +
           "\nMembership Level: " + membershipLevel +
           "\nDate of Joining: " + dateOfJoining +
           "\nMembership Status: " + membershipStatus +
           "\nExpiry Date: " + expiryDate;
    }

    
    
    
    
}
