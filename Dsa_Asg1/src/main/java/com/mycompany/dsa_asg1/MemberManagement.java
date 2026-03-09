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
    private Scanner scanner = new Scanner(System.in);
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
    
//FEE AND EXPIRY DATE - helper method
    //getRegistrationFee(String membershipLevel) , calculateExpiryDate(String joinOrRenewDate)
 //SEARCH
    //searchMemberById(String memberId) 
    //searchMembersByMembershipLevel(String membershipLevel)
    //searchMembersByMembershipStatus(String membershipStatus)
// VALIDATION
    //isDuplicateMemberId(String memberId) -Done
    //isValidGender(String gender) -Done
    //isValidContactNumber(String contactNumber) -Done
    //isValidMembershipLevel(String membershipLevel) -Done
    //isValidMembershipStatus(String membershipStatus) -Done
    //isValidAddress(String address) -Done
    //calculateAge(String dateOfBirth) -Done

    
   // ==========================================
    // +++++++  GET REGISTER FEE ++++++++ -DC
   // ==========================================
    public double getRegistrationFee(String membershipLevel) {
        
       if(membershipLevel.equalsIgnoreCase("Gold")){
           return 180.0;
       }else if(membershipLevel.equalsIgnoreCase("Platinum")) {
           return 220.0;
       }else if(membershipLevel.equalsIgnoreCase("Diamond")){
           return 300.0;
       }else{
           return 0.0;
       }  
    }

   // ==========================================
    // +++++++  GET EXPIRY DATE ++++++++ -DC
   // ==========================================
    public String calculateExpiryDate(String joinOrRenewDate) {
        try{ //prevet occur error when user is input invalid date format.
            
            LocalDate date = LocalDate.parse(joinOrRenewDate);
            String expiryDate = date.plusYears(1).toString(); //expiry date = baseDate(joinOrRenewDate) + 1 year
            return expiryDate;
        }catch(DateTimeParseException e){
            return null;
        }
    
    }

   // ==========================================
    // +++++++  VALIDATION METHOD ++++++++ -DC
   // ==========================================
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
        contactNumber = contactNumber.replace(" ", "");
        
        //Mobile Format  0123456789, 0137654321
       String mobileWithoutDash10 = "^01[02346789][0-9]{7}$";
       String mobileWithoutDash11 = "^011[0-9]{8}$";
       String mobileWithDash10 = "^01[02346789]-[0-9]{7}$";
       String mobileWithDash11 = "^011-[0-9]{8}$";
       
       //Landline Format 
       String landlineWithoutDash2DigitArea = "^0[345679][0-9]{7,8}$"; // 2-digit area code examples: 03-12345678, 04-1234567
       String landlineWithDash2DigitArea = "^0[345679]-[0-9]{7,8}$";
       String landlineWithoutDash3DigitArea = "^08[2-9][0-9]{6}$";  // 3-digit area code examples: 082 123456, 088 123456
       String landlineWithDash3DigitArea = "^08[2-9]-[0-9]{6}$";
       
       return contactNumber.matches(mobileWithoutDash10)
            || contactNumber.matches(mobileWithoutDash11)
            || contactNumber.matches(mobileWithDash10)
            || contactNumber.matches(mobileWithDash11)
            || contactNumber.matches(landlineWithoutDash2DigitArea)
            || contactNumber.matches(landlineWithDash2DigitArea)
            || contactNumber.matches(landlineWithoutDash3DigitArea)
            || contactNumber.matches(landlineWithDash3DigitArea);
       
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
            LocalDate dob = LocalDate.parse(dateOfBirth); //[ yyyy-mm-dd ] format
            LocalDate today = LocalDate.now();
            
            if (dob.isAfter(today)) {
                return -1;
            }
            return Period.between(dob, today).getYears(); // Calculated year exp: dob = 2000-05-20 - today = 2026-03-09 = 25year 9month 17days
            
        } catch (DateTimeParseException e) {
            return -1;
        }
    }
    

    public int getRegistrationFee(String membershipLevel){
		if(membershipLevel.equalsIgnoreCase("Gold")) {
			return 180;
		}else if(membershipLevel.equalsIgnoreCase("Platinum")) {
			return 220;
		}else if(membershipLevel.equalsIgnoreCase("Diamond")) {
			return 300;
		}
		return 0;//default
	}
	
	public int getRenewalFee(String membershipLevel) {
		if(membershipLevel.equalsIgnoreCase("Gold")) {
			return 80;
		}else if(membershipLevel.equalsIgnoreCase("Platinum")) {
			return 110;
		}else if(membershipLevel.equalsIgnoreCase("Diamond")) {
			return 150;
		}
		return 0;//default
	}
	
	public String calculateExpiryDate(String baseDate) {
		
		// 1. Use "-" to split the string into three parts(Array List):
		// parts[0] is date, parts[1] is month, parts[2] is year.
		String[] parts = baseDate.split("-"); 
		
		// 2. Convert the parts[2] into integer.
		int year = Integer.parseInt(parts[2]);
		
		// 3. Add 1 to the year
		year = year + 1;
		
		// 4. Reassemble them into a string using "-" and go back.
		String newExpiryDate = parts[0] + "-" + parts[1] + "-" + year;
		
		return newExpiryDate;
	}
	
	public void renewMembership(String memberId){
		/*
		 * 1. Find the member (by Member ID).
		 * 2. Display the renewal amount (calling the `getRenewalFee function).
		 * 3. Expiration date + 1 year (calling the `calculateExpiryDate` function).
		 * 4. Update the status to Active (in case it was previously Inactive).
		*/
		
		Member target = searchMemberById(memberId);
		
		String level;
		int fee;
		if(target==null) {
			System.out.println("Member is not found");
			return;
		}else {
			level = target.getMembershipLevel();
			fee=getRenewalFee(level);
		}
		
		String oldDate=target.getExpiryDate();
		String newDate=calculateExpiryDate(oldDate);
		target.setExpiryDate(newDate);
		
		target.setMembershipstatus("Active");
		
		System.out.println("Renewal Successful");
		System.out.println("Member ID: " + target.getMemberId());
		System.out.println("Membership Level: " + level);
		System.out.println("Renewal Fee: RM" + fee);
		System.out.println("New Expiry Date: " + newDate);
	}
	
	public void updateContactNumber(String memberId,String newContactNumber) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println("Member is not found");
		}else {
			if (isValidContactNumber(newContactNumber)) { 
	            target.setContactNumber(newContactNumber);
	            System.out.println("Update Successful!");
	        } else {
	            System.out.println("Error: Invalid contact number format!");
	        }
		}
	}
	
	public void updateAddress(String memberId,String address) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println("Member is not found");
		}else {
			if (isValidAddress(address)) { 
				target.setAddress(address);
		        System.out.println("Update Successful!");
	        } else {
	            System.out.println("Error: Invalid address format!");
	        }
		}
	}
	
	public void updateMembershipLevel(String memberId,String level) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println("Member is not found");
		}else {
			if (isValidMembershipLevel(level)) { 
				target.setMembershipLevel(level);
		        System.out.println("Update Successful!");
	        } else {
	            System.out.println("Error: Invalid membership level!");
	        }
		}
	}
	
	public void cancelMembership(String memberId) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).

		if(target==null) {
			System.out.println("Member is not found");
		}else {
			target.setMembershipStatus("Inactive");
	        System.out.println("Update Successful!");
		}
	}
	
	public void deleteMember(String memberId) {
		
		Member target = searchMemberById(memberId);//Find the member (by Member ID).
	    
		if(target==null) {
			System.out.println("Member is not found");
		}else {
			memberList.remove(target);
			System.out.println("Member has been completely deleted!");
		}

	}
    
    
}
