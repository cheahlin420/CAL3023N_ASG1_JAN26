/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_asg1;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class MemberManagement {
    private Scanner scanner;
    private LinkedList<Member> memberList = new LinkedList<>();
    
    // Add member
    public void registerMember(){
        
        String memberId; //No need setter (unique identifier)
        String name;
        String dateOfBirth;
        int age;
        String gender;
        String contactNumber;
        String address;
        String membershipLevel;
        String dateOfJoining;
        String membershipStatus;
        String expiryDate;
        
        System.out.println("\n=== Register New Member ===");
        
        // User Input
        boolean duplicate;
        do {
            System.out.print("Enter Member ID: ");
            memberId = scanner.nextLine().trim();
            duplicate = isDuplicateMemberId(memberId);
            
            if (memberId.isEmpty()) {
                System.out.println("Member ID cannot be empty.");
            } else if (duplicate) {
                System.out.println("Member ID already exists.");
            }
            
        } while (memberId.isEmpty() || duplicate);
    
    
    
    }
    
    
    // Validation method
    
//FEE AND EXPIRY DATE
    //getRegistrationFee() , calculateExpiryDate()
 //SEARCH
    //searchMemberById(String memberId) 
    //searchMembersByMembershipLevel(String membershipLevel)
    //searchMembersByMembershipStatus(String membershipStatus)
// VALIDATION
    //isDuplicateMemberId(String memberId)
    //isValidGender(String gender)
    //isValidContactNumber(String contactNumber)
    //isValidMembershipLevel(String membershipLevel)
    //isValidMembershipStatus(String membershipStatus)
    //isValidAddress(String address)
    //calculateAge(String dateOfBirth)

    
    
    public boolean isDuplicateMemberId(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female");
    }
    
    
    public boolean isValidContactNumber(String contactNumber) {
        contactNumber = contactNumber.trim();
        // Malaysia Mobile Format - 0123456789 / 012-3456789 
        // Malaysia Landline Format - 03-12345678 / 082-123456
        
        String mobileWithoutDash = "^(01[0-9])[0-9]{7,8}$";
        String mobileWithDash = "^(01[0-9])-[0-9]{7,8}$";
        String landlineWithDash = "^(0[3-9])-[0-9]{6,8}$";
        
        return contactNumber.matches(mobileWithoutDash) || contactNumber.matches(mobileWithDash) || contactNumber.matches(landlineWithDash);
    }
    
    public boolean isValidMembershipLevel(String membershipLevel) {
        return membershipLevel.equalsIgnoreCase("Gold") || membershipLevel.equalsIgnoreCase("Platinum") || membershipLevel.equalsIgnoreCase("Diamond");
    }
    
    public boolean isValidMembershipStatus(String membershipStatus) {
        return membershipStatus.equalsIgnoreCase("Active") || membershipStatus.equalsIgnoreCase("Inactive");
    }
    
    public boolean isValidAddress(String address) {
        return !address.trim().isEmpty();
    }
    
    public int calculateAge(String dateOfBirth) {
        try {
            LocalDate dob = LocalDate.parse(dateOfBirth);
            LocalDate today = LocalDate.now();
            
            if (dob.isAfter(today)) {
                return -1;
            }
            return Period.between(dob, today).getYears();
            
        } catch (DateTimeParseException e) {
            return -1;
        }
    }
    

    
    
    
}
