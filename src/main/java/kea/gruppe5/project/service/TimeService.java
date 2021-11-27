package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.GetMapping;

public class TimeService {

    @GetMapping("/time")
    public double calculateTime(){
        //Skal self slettes - brug variable totaltime eller hvad den hedder?
        double hej = 2;
        return hej;
    }
}
