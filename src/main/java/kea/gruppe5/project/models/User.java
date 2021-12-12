package kea.gruppe5.project.models;

public class User {
    String name;
    String personnelNumber;
    String email;
    int id;

    public User(String name,
                String personnelNumber,
                String email) {
        this.name = name;
        this.personnelNumber = personnelNumber;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
