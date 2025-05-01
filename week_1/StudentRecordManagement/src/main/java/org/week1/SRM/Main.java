package org.week1.SRM;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Scanner object to read user input.
        StudentManager studentManager = new StudentManager(); // Create an instance of StudentManager to manage student records.
        int choice; // Variable to store user choice.


        // Display menu options to the user.
        // There are may different ways to display the menu. This is one of the simplest ways. Using Do while loop to keep the menu running until the user chooses to exit.
        do {
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Read user choice.

            switch (choice) {
                case 1:
                    studentManager.addStudent(); // Call method to add a student.
                    break;
                case 2:
                    studentManager.displayStudents(); // Call method to display all students.
                    break;
                case 3:
                    System.out.print("Enter student ID to search: ");
                    int idToSearch = scanner.nextInt();
                    studentManager.searchStudentById(idToSearch); // Call method to search for a student by ID.
                    break;
                case 4:
                    studentManager.updateStudentById(); // Call method to update a student's information.
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5); // Loop until the user chooses to exit.

        scanner.close(); // Close the scanner to prevent resource leaks.

        // There is another way to display the menu. Just using a switch case to display the menu. This is not a good way to display the menu. This is just for demonstration purpose.
        //askMenu();
    }

    private static void askMenu() {
        Scanner scanner = new Scanner(System.in);
        showMenu();
        System.out.println("Do you want to exit? (y/n)");
        String exitChoice = scanner.next();
        if (exitChoice.equalsIgnoreCase("y")) {
            System.out.println("Exiting...");
        } else if (exitChoice.equalsIgnoreCase("n")) {
            System.out.println("Continuing...");
            showMenu();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void showMenu() {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student by ID");
        System.out.println("5. Delete Student by ID");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                studentManager.addStudent();
                askMenu();
                break;
            case 2:
                studentManager.displayStudents();
                askMenu();
                break;
            case 3:
                System.out.print("Enter student ID to search: ");
                int idToSearch = scanner.nextInt();
                studentManager.searchStudentById(idToSearch);
                askMenu();
                break;
            case 4:
                studentManager.updateStudentById();
                askMenu();
                break;
            case 5:
                studentManager.deleteStudentById();
                askMenu();
                break;
            case 6:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }
}