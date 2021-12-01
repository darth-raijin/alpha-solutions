package kea.gruppe5.project.controllers;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kea.gruppe5.project.utility.DatabaseConnectionManager;

@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "root";
    }

    @GetMapping("/database")
    public String databaseConnection() {
        DatabaseConnectionManager.getConnection();
        return "root";
    }

}
