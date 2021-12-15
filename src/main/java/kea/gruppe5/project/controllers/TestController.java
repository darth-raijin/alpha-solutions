package kea.gruppe5.project.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

    @Controller
    public class TestController {
        @GetMapping ("/")
        public String index() {
            return "root";
        }
        @GetMapping("/database")
        public String databaseConnection() {
            DatabaseConnectionManager.getConnection();
            return "root";
        }
        @GetMapping("/create")
        public String create(){
            return "create";
        }
    }

