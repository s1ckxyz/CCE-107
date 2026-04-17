package JOption;
import javax.swing.JOptionPane;
import java.io.*;
public class FeedbackSystem {

	public static void main(String[] args) {
        int totalEntries = 0;
        int sumOfRatings = 0;
        int excellent = 0, good = 0, average = 0, poor = 0, veryPoor = 0;
        
        StringBuilder allRecords = new StringBuilder("    Student Feedback Records\n\n");
        boolean addMore = true;

        while (addMore) {
            String name = JOptionPane.showInputDialog("Enter Student Name:");
            String course = JOptionPane.showInputDialog("Enter Course/Subject:");
            String message = JOptionPane.showInputDialog("Enter Feedback Message:");
            
            // (LOOP) rating 1-5 only
            int rating = 0;
            boolean isValid = false;
            
            while (!isValid) {
                try {
                    String ratingStr = JOptionPane.showInputDialog("Enter Rating (1-5):");
                    
                    // bawal mag "Cancel" phase
                    if (ratingStr == null) {
                        JOptionPane.showMessageDialog(null, "Rating is required to proceed.");
                        continue;
                    }

                    rating = Integer.parseInt(ratingStr);

                    if (rating >= 1 && rating <= 5) {
                        isValid = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Please enter a number from 1 to 5 only.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric digit (1-5).");
                }
            }
            // Feedback Scale

            String category = "";
            switch (rating) {
                case 5: category = "Excellent"; excellent++; break;
                case 4: category = "Good"; good++; break;
                case 3: category = "Average"; average++; break;
                case 2: category = "Poor"; poor++; break;
                case 1: category = "Very Poor"; veryPoor++; break;
            }

            totalEntries++;
            sumOfRatings += rating;

            String record = String.format(
                "Student Name: %s\nCourse: %s\nFeedback: %s\nRating: %d (%s)\n\n",
                name, course, message, rating, category
            );
            allRecords.append(record);

            int choice = JOptionPane.showConfirmDialog(null, "Do you want to add another entry?", "Continue?", JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                addMore = false;
            }
        }

        double averageRating = (double) sumOfRatings / totalEntries;

        String feedbackStatus = "";
        if (averageRating >= 4.5) feedbackStatus = "Outstanding Feedback!";
        else if (averageRating >= 3.5) feedbackStatus = "Good Feedback!";
        else if (averageRating >= 2.5) feedbackStatus = "Average Feedback";
        else feedbackStatus = "Needs Improvement";

        String summary = String.format(
            "\nTotal Feedbacks: %d\nAverage Rating: %.1f\nStatus: %s\n\n" +
            "Rating Summary:\nExcellent: %d\nGood: %d\nAverage: %d\nPoor: %d\nVery Poor: %d\n" +
            "",
            totalEntries, averageRating, feedbackStatus, excellent, good, average, poor, veryPoor
        );

        allRecords.append(summary);

        // display results
        JOptionPane.showMessageDialog(null, summary, "Feedback Results", JOptionPane.INFORMATION_MESSAGE);

        // save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
            writer.write(allRecords.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Data successfully saved to feedback.txt!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
        }
    }
}