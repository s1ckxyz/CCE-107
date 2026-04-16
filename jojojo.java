package ccee;
import javax.swing.JOptionPane;
public class jojojo {

	public static void main(String[] args) {
        int[][] hotel = new int[7][5];
        String[] options = {"View Rooms", "Check In", "Check Out", "Exit"};

        while (true) {
            // Using JOptionPane.showOptionDialog to create the main menu
            int choice = JOptionPane.showOptionDialog(
                    null, 
                    "Welcome to the Hotel Reservation System\nPlease select an action:", 
                    "Hotel Management", 
                    JOptionPane.YES_NO_CANCEL_OPTION, // Use standard option types
                    JOptionPane.PLAIN_MESSAGE,
                    null, 
                    options, 
                    options[0]);

            // If user closes the window or clicks "Exit"
            if (choice == 3 || choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Exiting... Thank you!", "System Message", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (choice) {
                case 0:
                    viewRooms(hotel);
                    break;
                case 1:
                    checkIn(hotel);
                    break;
                case 2:
                    checkOut(hotel);
                    break;
            }
        }
    }

    public static void viewRooms(int[][] hotel) {
        StringBuilder status = new StringBuilder("--- Current Room Status ---\n\n");
        for (int i = 6; i >= 0; i--) {
            status.append("Floor ").append(i + 1).append(":  ");
            for (int j = 0; j < 5; j++) {
                // Using a slightly cleaner visual for the room status
                String icon = (hotel[i][j] == 0) ? "[ ]" : "[X]"; 
                status.append(icon).append(" ");
            }
            status.append("\n");
        }
        status.append("\n[ ] = Available  [X] = Occupied");
        JOptionPane.showMessageDialog(null, status.toString(), "Room Map", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void checkIn(int[][] hotel) {
        try {
            String fInput = JOptionPane.showInputDialog("Enter floor (1-7):");
            if (fInput == null) return; // User pressed cancel
            int f = Integer.parseInt(fInput) - 1;

            String rInput = JOptionPane.showInputDialog("Enter room (1-5):");
            if (rInput == null) return; // User pressed cancel
            int r = Integer.parseInt(rInput) - 1;

            if (f >= 0 && f < 7 && r >= 0 && r < 5) {
                if (hotel[f][r] == 0) {
                    // Instruction: Use showConfirmDialog to confirm check-in
                    int confirm = JOptionPane.showConfirmDialog(null, 
                            "Are you sure you want to Check-In to Floor " + (f + 1) + " Room " + (r + 1) + "?", 
                            "Confirm Check-In", 
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        hotel[f][r] = 1;
                        JOptionPane.showMessageDialog(null, "Check-in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Room already occupied!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Floor/Room number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void checkOut(int[][] hotel) {
        try {
            String fInput = JOptionPane.showInputDialog("Enter floor (1-7):");
            if (fInput == null) return;
            int f = Integer.parseInt(fInput) - 1;

            String rInput = JOptionPane.showInputDialog("Enter room (1-5):");
            if (rInput == null) return;
            int r = Integer.parseInt(rInput) - 1;

            if (f >= 0 && f < 7 && r >= 0 && r < 5) {
                if (hotel[f][r] == 1) {
                    // Instruction: Use showConfirmDialog to confirm check-out
                    int confirm = JOptionPane.showConfirmDialog(null, 
                            "Confirm check-out for Floor " + (f + 1) + " Room " + (r + 1) + "?", 
                            "Confirm Check-Out", 
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        hotel[f][r] = 0;
                        JOptionPane.showMessageDialog(null, "Check-out successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Room is already empty!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Floor/Room number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}