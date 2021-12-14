package kea.gruppe5.project.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineService {
    double workHours = 8;

    public static void calculateTime(String deadlineDate) {
        // Resource for calculating time 
        // https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-java

        // Current date is retrieved in dd/MM/yyyy format
        String currentDate = java.time.LocalDate.now().toString();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Formatting deadline date
        String[] splitDeadline = deadlineDate.split("-");
        String deadline = splitDeadline[2] + splitDeadline[1] + splitDeadline[0];
        try {
            LocalDateTime current = LocalDate.parse(currentDate, dateFormatter);
            LocalDateTime date2 = LocalDate.parse(deadline, dateFormatter);
            long difference = Duration.between(current, deadline).toDays();
            System.out.println ("Days: " + daysBetween);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
}
