package kea.gruppe5.project.service;
//MM, IPN

import kea.gruppe5.project.models.Project;
import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.repository.SubprojectRepository;
import org.thymeleaf.util.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class SubProjectService {

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        return SubprojectRepository.getSubprojectsByParentId(parentId);
    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Efter oprettelse af SP, skal Id returneres for at redirect
        int createdSub = SubprojectRepository.createSubproject(name, description, Integer.parseInt(id));

        if(createdSub >= 0) {
            System.out.println("Subproject created - ID: " + createdSub);
        } else {
            System.out.println("Failed to create subproject");
        }
        System.out.println("Subproject created - ID: " + createdSub);

        return createdSub;
    }

    public static Subproject getSubProjectById(int subprojectID) {
        return SubprojectRepository.getSubprojectById(subprojectID);
    }

    public static boolean updateSubProject(String name, String description, int id) {
        return SubprojectRepository.updateSubproject(name, description, id);
    }

    private static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }

    public static double[] getTotalTime(int projectId) throws ParseException {
        double[] result = new double[3];
        // Fetches all subprojects that belong to project. For each Subproject, total time will be calculated for Task and Subtasks, and set to Subproject time
        ArrayList<Subproject> subprojects = SubprojectRepository.getSubprojectsByParentId(projectId);
        double totalTime = 0;

        //Calculates days until deadline
        long miliseconds = System.currentTimeMillis();
        Date today = new Date(miliseconds);
        String projectIdStr = Integer.toString(projectId);
        Date deadlineDate = new SimpleDateFormat("dd/MM/yyyy").parse(ProjectService.getProjectById(projectIdStr).getDeadline());
        int daysLeft = getWorkingDaysBetweenTwoDates(today, deadlineDate);

        for (Subproject subproject : subprojects) {
            // First approach is taken to calculate the time
            double subprojectTime = TaskService.getTotalTime(subproject.getId());
            System.out.println("Subproject ID: " + subproject.getId() + " has time: " + subprojectTime);
            totalTime += subprojectTime;

            SubprojectRepository.updateTime(subproject.getId(), totalTime);
        }

        //Calculate hours per day
        double hoursADay=0;

        if (daysLeft>0) {
            hoursADay=(Math.round(100*totalTime/daysLeft));
            hoursADay = hoursADay/100;
            System.out.println(hoursADay);
        }
        result[0] = totalTime;
        result[1] = daysLeft;
        result[2] = hoursADay;
        return result;
    }

}
