	package com.mycompany.dsa_asg1;

	import java.util.Scanner;
	import java.util.List;
	import java.lang.reflect.Member;
	import java.util.LinkedList;

	public class Driver {
		private Scanner input = new Scanner (System.in);
		private MemberManagement manager = new MemberManagement();
		
		public static void main (String[]args) {
			Driver program = new Driver();
			program.showMainMenu();
			
		}
		
			public void showMainMenu() {
				
			int choice = -1;
			while (choice != 0) {
				
				System.out.println(" \n===== CLUB MEMBER MANAGEMENT SYSTEM =====");
				System.out.println("1. Register New Member ");
				System.out.println("2. Search Member ");
				System.out.println("3. Update Member Information ");
				System.out.println("4. Renew Membership ");
				System.out.println("5. Delete Member ");
				System.out.println("6. View All Members ");
				System.out.println("7. View Membership Status ");
				System.out.println("8. View Membership Level ");
				System.out.println("0. Exit ");
				System.out.println("\nPlease select an option: ");
				
				try {
					
				choice = Integer.parseInt(input.nextLine());
				handleMenuChoice(choice);
				
				}catch (NumberFormatException e) {
					System.out.println("Error: Please enter a valid number. ");
				}
			}
		}			
				
			private void handleMenuChoice(int choice) {
				switch (choice) {
				
				case 1:
					manager.registerMember(); // call A's method
					break;
					
				case 2:
					System.out.print("Enter Member ID: ");
					String memID = input.nextLine();
					displayMembersDetails(manager.searchMemberById(memID));// call A
					break;
					
				case 3:
					System.out.println("Enter Member ID to update: ");
					String updateMem = input.nextLine();
					manager.updateMemberInformation(updateMem);// call B
					break;
					
				case 4:
					System.out.println("Enter Member ID to renew: ");
					String renewMem = input.nextLine();
					manager.renewMembership(renewMem); // call B
					break;
				
				case 5:
					System.out.println("Enter Member ID to delete: ");
					String deleteMem = input.nextLine();
					manager.deleteMember(deleteMem);// call B
					break;
					
				case 6:
					viewALlMembers();
					break;
				
				case 7:
					System.out.println("Enter Membership Satus: ");
					viewMembersByStatus(input.nextLine());
					break;
					
				case 8:
					System.out.println("Enter Membership Level: ");
					viewMembersByLevel(input.nextLine());
					break;
					
				case 0:
					System.out.println("Exiting System...Goodbye! ");
					break;
				
				default:
					System.out.println("Invalid choice!");				
			
				}
			}
			
			// formating
			public void displayMemberDetails (Member m) {
				if (m == null) {
					System.out.println("Error: Member not found. ");
				}else {
					System.out.println("\n===== DETAILS =====");
					System.out.println(m.toString());
				}
		}
			//View all
			public void viewAllMembers(){
				List<Member> list = manager.getMemberList();
				if (list.isEmpty()) {
					System.out.println("No members in system. ");
					return;
				}
				
				System.out.printf("\n%-15s %-20s %-15s %-10s\n", "ID", "Name", "Membership Level", "Membership Status");
				System.out.println("------------------------------------------------------------------------------------");
				for (Member m : list) {
					System.out.printf("%-15s %-20s %-15s %-10s\n" , m.getMemberId(), m.getName(), m.getMembershipLevel(), m.getMembershipStatus());
					
				}
			}
			
			public void viewMembersByStatus(String status) {
				for (Member m : manager.getMemberList()) {
					if (m.getMembershipStatus().equalsIgnoreCase(status)) {
						displayMemberDetails(m);
						
					}
				}
			}
			
			public void viewMembersByLevel(String level) {
				for (Member m : manager.getMemberList()) {
					if (m.getMembershipLevel().equalsIgnoreCase(level)) {
						displayMemberDetails(m);
					}
				}
			}
			

	}

