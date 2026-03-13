/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_asg1;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class MemberManagement {
    
    
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<Member> memberList = new LinkedList<>();
    
//    //-----------------------------------------------------------------
//    //Testing method
//    public void addTestMember(Member member) {
//        if (!isDuplicateMemberId(member.getMemberId())) {
//            memberList.add(member);
//        } else {
//            System.out.println("Duplicate Member ID found. Test member not added.");
//        }
//    }
//    //-----------------------------------------------------------------
    
    // Add member
    public void registerMember(){
        LocalDate today = LocalDate.now();
   
        String memberId = generateMemberId(); //Cannot setter (unique identifier)
        String name;
        String dateOfBirth;
        int age;
        String gender;
        String contactNumber;
        String address;
        String membershipLevel;
        String dateOfJoining = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String membershipStatus;
        String expiryDate = calculateExpiryDate(dateOfJoining);
        
   // ==========================================
    //      +++++++ USER INPUT ++++++++ -DC
   // ==========================================
        System.out.println("\n=== Register New Member ===");
        System.out.println("Member ID: " + memberId);
        System.out.println("-----------------------");
        
        // Enter name
        do {
            System.out.print("Enter Member Name: ");
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            }
        } while (name.isEmpty());
        
    // Date of Birth + Age
    do {
        System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
        dateOfBirth = scanner.nextLine().trim();
        
        age = calculateAge(dateOfBirth);

        if (age == -1) {
            System.out.println("Invalid date of birth.");
            System.out.println("Please use dd-mm-yyyy and ensure it is not a future date.");
        }
    } while (age == -1);
    
    
    // Gender
    do {
        System.out.print("Enter Gender (Male/Female): ");
        gender = scanner.nextLine().trim();

        if (!isValidGender(gender)) {
            System.out.println("Invalid gender. Please enter Male or Female.");
        }
    } while (!isValidGender(gender));
    
    // Contact Number
    do {
        System.out.print("Enter Contact Number: ");
        contactNumber = scanner.nextLine().trim();

        if (!isValidContactNumber(contactNumber)) {
            System.out.println("Invalid contact number format.");
            System.out.println("Examples:");
            System.out.println("Mobile: 0123456789 / 012-3456789 / 01123456789 / 011-23456789");
            System.out.println("Landline: 03-12345678 / 0312345678 / 082-123456 / 082123456");
        }
    } while (!isValidContactNumber(contactNumber));
    
    // Address
    do {
        System.out.print("Enter Address: ");
        address = scanner.nextLine().trim();

        if (!isValidAddress(address)) {
            System.out.println("Address cannot be empty.");
        }
    } while (!isValidAddress(address));
    
    // Membership Level
    do {
        System.out.print("Enter Membership Level (Gold/Platinum/Diamond): ");
        membershipLevel = scanner.nextLine().trim();

        if (!isValidMembershipLevel(membershipLevel)) {
            System.out.println("Invalid membership level. Please enter Gold, Platinum, or Diamond.");
        }
    } while (!isValidMembershipLevel(membershipLevel));
    
    // Membership Status
    do {
        System.out.print("Enter Membership Status (Active/Inactive): ");
        membershipStatus = scanner.nextLine().trim();

        if (!isValidMembershipStatus(membershipStatus)) {
            System.out.println("Invalid membership status. Please enter Active or Inactive.");
        }

    } while (!isValidMembershipStatus(membershipStatus));
    
     // Registration Fee
    double registrationFee = getRegistrationFee(membershipLevel);
        
    Member newMember = new Member(
            memberId,
            name, 
            dateOfBirth,
            age,
            gender,
            contactNumber,
            address,
            membershipLevel,
            dateOfJoining,
            membershipStatus,
            expiryDate
    );
    
    // Insert member into LinkedList
    memberList.add(newMember);
    
    // Display output here!
    System.out.println("\n======================================");
    System.out.println("=      Registration Successful       =");
    System.out.println("======================================");
    System.out.println("Registration Fee | " + registrationFee);
    System.out.println("Expiry Date      | " + expiryDate);
    System.out.println("--------------------------------------");
    System.out.println("Member Details");
    System.out.println("-------------------");
    System.out.println(newMember);
    System.out.println("--------------------------------------");
        
    }
    
    
   // Validation method
    
//FEE AND EXPIRY DATE - helper method
    //getRegistrationFee(String membershipLevel) , calculateExpiryDate(String joinOrRenewDate) - Done
 //SEARCH
    //searchMemberById(String memberId) -Suki Done
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
// ADDITIONAL
    // isLeapYear //isValidDate

    
    //==========================================
    // +++++++ GENERATE MEMBER ID ++++++++ -DC
   // ==========================================
    public String generateMemberId() {
        int nextNumber = memberList.size() + 1;
        String newId = String.format("M%04d", nextNumber);
        
        while(isDuplicateMemberId(newId)){
            nextNumber++;
            newId = String.format("M%04d", nextNumber);
        }
        return newId;
    }
    
    
    
    
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
    // ++++    UPDATE MEMBERSHIP STATUS   +++++ -DC
   // ==========================================
    public void updateMembershipStatusByExpiry(){
        LocalDate today = LocalDate.now();
        boolean updated = false;
        
        for(Member member : memberList){
            String expiryDate = member.getExpiryDate();
            
            if(isValidDate(expiryDate)){
                LocalDate expiry = convertToLocalDate(expiryDate);
                
                if(expiry.isBefore(today) && !member.getMembershipStatus().equalsIgnoreCase("Inactive")){
                    member.setMembershipStatus("Inactive");
                    updated = true;
                }
            }
        }
        
        if(updated) {
            System.out.println("Membership status have been updated based on expiry dates.");
        }else{
            System.out.println("No memebership status updates were needed.");   
        }   
    }
    
    
   // ==========================================
    // +++++++  SEARCH METHOD ++++++++ -DC - we using linear search(linked list)
   // ==========================================
    
    // - Search Membership by Id -Suki
    
    /* SUKI
     * 1. Search
     * searchMemberById
     * 
     * 2. Renew
     * calculateExpiryDate(String baseDate)
     * renewMembership(String memberId)
     * 
     * 3. Update
     * updateContactNumber(String memberId,String newContactNumber)
     * updateAddress(String memberId,String address)
     * updateMembershipLevel(String memberId,String level)
     * cancelMembership(String memberId)
     * 
     * 4. Delete
     * deleteMember(String memberId)
     * 
     * 5. Text file(Storage)
     * saveToFile()
     * loadFromFile()
     * 
     * 6. Testing
     */
    
	
    public Member searchMemberById(String memberId){
    	for(int i =0;i<memberList.size();i++) {
    		Member m=memberList.get(i);
    		if(m.getMemberId().equals(memberId)) {
    			return m;
    		}
    	}
    	return null;
    }
    
    // - Search Membership by level
    public void searchMembersByMembershipLevel(String membershipLevel) {
        // check first it's the level is valid
        if (!isValidMembershipLevel(membershipLevel)) {
            System.out.println("Invalid membership level.");
            System.out.println("Please enter Gold, Platinum, or Diamond.");
            return;
        }
        boolean found = false;
        
        System.out.println("\n=== Search Result by Membership Level: " + membershipLevel + " ===");
        for (Member member : memberList) {
            if (member.getMembershipLevel().equalsIgnoreCase(membershipLevel)) {
                System.out.println(member);
                System.out.println("-----------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No members found with membership level: " + membershipLevel);
        }
    }
    
    // - Search Membership by status
    //First, check whether the input status is valid
    public void searchMembersByMembershipStatus(String membershipStatus){
        boolean found = false;
        
        // to update the latest version before search.
        updateMembershipStatusByExpiry();
        
        if(!isValidMembershipStatus(membershipStatus)){
            System.out.println("Invalid membership status.");
            System.out.println("Please enter Active or Inactive.");
            return;
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("                MEMBER STATUS              ");
        System.out.println("-------------------------------------------");
        System.out.println("          " + membershipStatus + "         ");
        
        for(Member member : memberList) {
            if(member.getMembershipStatus().equalsIgnoreCase(membershipStatus)) {
                System.out.println("-------------------------------------------");
                 System.out.println(member);
                 found = true;
            }
        }
        
        if (!found) {
            System.out.println("No member found with membership status: " + membershipStatus);
        }
    }
    
    
   // ==========================================
    // +++++++  Date Convertor ++++++++ -DC
   // ==========================================
    public LocalDate convertToLocalDate(String dateStr) {
        String[] parts = dateStr.split("-");
        
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        
        return LocalDate.of(year, month, day);
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
        if (!isValidDate(dateOfBirth)) {
            return -1;
        }
        
        LocalDate dob = convertToLocalDate(dateOfBirth);
        LocalDate today = LocalDate.now();
        
        if (dob.isAfter(today)) {
            return -1;
        }
        
        return Period.between(dob, today).getYears();
    }

    
    // Check the user input date Format
    public boolean isValidDate(String dateStr) {
        if (!dateStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        
        String[] parts = dateStr.split("-");
        
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check valid month
        if (month < 1 || month > 12) {
            return false;
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
		//if(!isValidDate(baseDate)) {
		//	return;
		//}
		//format = dd-mm-yyyy
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
        
        // ====================================================== DC PART DONE HERE ======================================================
	
	public void renewMembership(String memberId){
		/*
		 * 1. Find the member (by Member ID).
		 * 2. Display the renewal amount (calling the `getRenewalFee function).
		 * 3. Expiration date + 1 year (calling the `calculateExpiryDate` function).
		 * 4. Update the status to Active (in case it was previously Inactive).
		*/
		
		Member target = searchMemberById(memberId);
		
		String level;
		double fee;
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
		
		target.setMembershipStatus("Active");
		
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
			return;
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
			return;
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
			return;
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
			return;
		}else {
			memberList.remove(target);
			System.out.println("Member has been completely deleted!");
		}

	}
	
	//Text file persistent storage
	public void saveToFile() {
	    try {
	        // 1. write text into "members.txt".
	        PrintWriter writer = new PrintWriter(new FileWriter("members.txt"));
	        
	        // 2. Call out everyone on the member list one by one.
	        for (int i = 0; i < memberList.size(); i++) {
	        	
	        	Member m = memberList.get(i);
	        	
	            // 3. Store each member's the data into record
	            String record = m.getMemberId() + ";" + m.getName() + ";" + m.getDateOfBirth() + ";" +m.getAge() + ";" + 
	            m.getGender() + ";" + m.getContactNumber() + ";" + m.getAddress() + ";" + m.getMembershipLevel() + ";" +
	            m.getDateOfJoining() + ";" + m.getMembershipStatus() + ";" + m.getExpiryDate();
	            
	            // 4. write this string of text into a file and add a newline character (println):
	            writer.println(record); 
	        }
	        
	        writer.close(); 
	        System.out.println("Store the data into members.txt successfully!");
	        
	    } catch (Exception e) {
	        System.out.println("Store unsuccessful：" + e.getMessage());
	    }
	}
	
	public void loadFromFile(){
		
		try {
			File file = new File("members.txt");
			
			// If the file doesn't exist at all (e.g., the first time using the system)
			// then do nothing and just end the process.
			if (!file.exists()) 
	            return;
			
			//2. use scanner to read the text in the document
			Scanner fileScanner = new Scanner(file);
			
			//3. Keep reading as long as there is another line in the file
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				
				String[] data =line.split(";");
				if(data.length == 11) {
					Member m = new Member (data[0],data[1],data[2],Integer.parseInt(data[3]),data[4],
							data[5],data[6],data[7],data[8],data[9],data[10]);
					memberList.add(m);
				}	
			}
			
			fileScanner.close();
	        System.out.println("Load data successfully!");
	        
		}catch (Exception e) {
	        System.out.println("Load unsuccessful：" + e.getMessage());
	    }
	}
    
	
}
