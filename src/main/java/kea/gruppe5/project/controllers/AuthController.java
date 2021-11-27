package kea.gruppe5.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.utility.DatabaseConnectionManager;

@Controller
@RequestMapping("auth")
public class AuthController {

    @GetMapping("/login")
    public String loginView() {
        System.out.println("LOGIN VIEW HIT");
        return "auth/login";
    }

    @PostMapping ("/login")
    public void login (@RequestParam String email,
                       @RequestParam String password) {

    }

    @GetMapping("/register")
    public String registerView() {
        return "auth/register";
    }

    @PostMapping ("/register")
    public void register (@RequestParam String name,
                          @RequestParam String password) {

    }

    @GetMapping("/signout")
    public String signoutView() {
        // Get Request for at logge ud

        // TODO Session slettes bum bum fixet

        return "auth/signout"; // Skal return en redirect til "/" ruten
    }


}
