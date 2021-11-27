package kea.gruppe5.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.service.AuthService;
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
    public void login (@RequestParam MultiValueMap body) {
        String email = String.valueOf(body.get("email")).replace("[","").replace("]","");
        String password = String.valueOf(body.get("password")).replace("[","").replace("]","");

        // TODO send til AuthService
        AuthService.authenticateUser(email, password);



    }

    @GetMapping("/register")
    public String registerView() {
        return "auth/register";
    }

    @PostMapping ("/register")
    public void register (@RequestParam MultiValueMap body) {
        String fullName = String.valueOf(body.get("fullName")).replace("[","").replace("]","");
        String email = String.valueOf(body.get("email")).replace("[","").replace("]","");
        String password = String.valueOf(body.get("password")).replace("[","").replace("]","");
        String address = String.valueOf(body.get("address")).replace("[","").replace("]","");
        String city = String.valueOf(body.get("city")).replace("[","").replace("]","");
        String postalCode = String.valueOf(body.get("postalCode")).replace("[","").replace("]","");
        String phoneNumber = String.valueOf(body.get("phoneNumber")).replace("[","").replace("]","");
    }

    @GetMapping("/signout")
    public String signoutView() {
        // Get Request for at logge ud

        // TODO Session slettes bum bum fixet

        return "auth/signout"; // Skal return en redirect til "/" ruten
    }


}
