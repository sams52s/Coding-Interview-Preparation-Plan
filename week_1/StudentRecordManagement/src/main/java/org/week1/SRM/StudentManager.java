package org.week1.SRM;

import java.util.Scanner;

public class StudentManager {
    private static final int MAX_STUDENTS = 40; // Maximum number of students. Static constant so that it can be used in static methods. Final for immutability.
    private final Student[] students = new Student[MAX_STUDENTS]; // Array to hold student objects.
    private final Scanner scanner = new Scanner(System.in);
    private int studentCount = 0; // Counter for the number of students added.

    public void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum limit reached.");
            return;
        }

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student department: ");
        String department = scanner.nextLine();
        students[studentCount++] = new Student(id, name, department);
        System.out.println("Student added successfully.");
        displayStudents();
    }

    public void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("Student List:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("ID: " + students[i].getId() + ", Name: " + students[i].getName() + ", Department: " + students[i].getDepartment());
        }
    }

    public void searchStudentById(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                System.out.println("Student found: ID: " + students[i].getId() + ", Name: " + students[i].getName() + ", Department: " + students[i].getDepartment());
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void updateStudentById() {
        System.out.println("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new department: ");
                String department = scanner.nextLine();

                students[i] = new Student(id, name.trim(), department.trim());
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void deleteStudentById() {
        System.out.println("Enter student ID to delete: ");
        int id = scanner.nextInt();

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId() == id) {
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[studentCount - 1] = null; // Clear the last element
                studentCount--;
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

}
