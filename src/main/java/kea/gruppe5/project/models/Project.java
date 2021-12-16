package kea.gruppe5.project.models;
//IPN
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Project {
    int personnelNumber;
    double totalTime;
    String name;
    String description;
    ArrayList<User> workersList;
    Boolean isUpdated;
    String deadline;
    double hoursADay;
    double daysLeft;

    public Project(int personnelNumber,
                   double totalTime,
                   String name,
                   String description,
                   ArrayList<User> workersList,
                   Boolean isUpdated,
                   int id) {
        this.personnelNumber = personnelNumber;
        this.totalTime = totalTime;
        this.name = name;
        this.description = description;
        this.workersList = workersList;
        this.isUpdated = isUpdated;
        this.id = id;
    }

    public String getDeadline() {
        return deadline;
    }
    public String getDeadlineHTML() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(deadline);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }



    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getPersonnelNumber(){
        return personnelNumber;
    }
    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }
    

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<User> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(ArrayList<User> workersList) {
        this.workersList = workersList;
    }


    public Boolean getUpdated() {
        return isUpdated;
    }

    public void setUpdated(Boolean updated) {
        isUpdated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    public double getHoursADay() {
        return hoursADay;
    }

    public void setHoursADay(double hoursADay) {
        this.hoursADay = hoursADay;
    }

    public double getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(double daysLeft) {
        this.daysLeft = daysLeft;
    }





}
