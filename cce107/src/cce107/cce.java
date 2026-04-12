package cce107;

import java.util.Scanner;

public class cce {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] hotel = new int[7][5];
		int choice = 0;
		while (choice != 4) {
			System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
			System.out.println("1. View Rooms");
			System.out.println("2. Check In");
			System.out.println("3. Check Out");
			System.out.println("4. Exit");
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			// if else
			if (choice == 1) {
				viewRooms(hotel);
			} 
			else if (choice == 2) {
				checkIn(scanner, hotel);
			} 
			else if (choice == 3) {
				checkOut(scanner, hotel);
			} 
			else if (choice == 4) {
				System.out.println("Exiting... Thank you!");
			} 
			else {
				System.out.println("Invalid choice! Please try again.");
			}
		}
		scanner.close();
	}
	// view rooms
	public static void viewRooms(int[][] hotel) {
		System.out.println("\n--- Current Status ---");
		for (int i = 6; i >= 0; i--) {
			System.out.print("Floor " + (i + 1) + ": ");
			for (int j = 0; j < 5; j++) {
				System.out.print("[" + hotel[i][j] + "]");
			}
			System.out.println();
		}
	}
	// check in
	public static void checkIn(Scanner scanner, int[][] hotel) {
		System.out.print("Enter floor (1-7): ");
		int f = scanner.nextInt() - 1;
		System.out.print("Enter room (1-5): ");
		int r = scanner.nextInt() - 1;
		if (hotel[f][r] == 0) {
			hotel[f][r] = 1;
			System.out.println("Check-in successful!");
		} else {
			System.out.println("Room already occupied!");
		}
	}
	// check out
	public static void checkOut(Scanner scanner, int[][] hotel) {
		System.out.print("Enter floor (1-7): ");
		int f = scanner.nextInt() - 1;
		System.out.print("Enter room (1-5): ");
		int r = scanner.nextInt() - 1;
		if (hotel[f][r] == 1) {
			hotel[f][r] = 0;
			System.out.println("Check-out successful!");
		} else {
			System.out.println("Room already empty!");
		}
	}
}

