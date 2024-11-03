package com.example.lab3;

public class Employee {
    private int id;
    private String name;
    private String position; // Changed from doctor to position
    private int salary;      // Changed from room to salary

    // Updated constructor
    public Employee(int id, String name, String position, int salary) {
        this.id = id;
        this.name = name;
        this.position = position; // Updated
        this.salary = salary;      // Updated
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() { // Updated getter
        return position; // Updated
    }

    public int getSalary() { // Updated getter
        return salary; // Updated
    }
}


