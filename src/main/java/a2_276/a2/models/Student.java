package a2_276.a2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String hairColor;
    private String shape;
    private int height;
    private int weight;
    private double gpa;

    public Student() {}

    public Student(String name, String hairColor, String shape, int height, int weight, double gpa) {
        this.name = name;
        this.hairColor = hairColor;
        this.height = height;
        this.weight = weight;
        this.gpa = gpa;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
    
}
