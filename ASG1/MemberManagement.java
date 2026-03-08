package ASG1;
import java.util.*;
public class MemberManagement {
	
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