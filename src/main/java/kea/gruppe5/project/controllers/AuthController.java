package kea.gruppe5.project.controllers;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

import kea.gruppe5.project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kea.gruppe5.project.models.User;
import kea.gruppe5.project.service.AuthService;
import kea.gruppe5.project.utility.DatabaseConnectionManager;
import org.thymeleaf.engine.HTMLAttributeDefinition;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("auth")
public class AuthController {

    public void wipeSession(HttpSession session) {

    }

    @GetMapping("/login")
    public String loginView(HttpSession session) {
        if (session.getAttribute("email") != null) {
                session.removeAttribute("personnelNumber");
                session.removeAttribute("name");
                session.removeAttribute("email");
        }
        return "auth/login";
    }

    @PostMapping ("/login")
    public String login (@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs, HttpSession session) {
        String email = String.valueOf(body.get("email")).replace("[","").replace("]","");
        String password = String.valueOf(body.get("password")).replace("[","").replace("]","");

        User user = AuthService.authenticateUser(email, password, session);
        
        if(user != null) {
            System.out.println("User successfully authenticated!");
            // Session skal indeholde PersonnelNumber, Navn og Email
            System.out.println(user.getEmail() + " " + user.getName());
            
            session.setAttribute("personnelNumber", user.getPersonnelNumber());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("name", user.getName());

            System.out.println(session.getAttributeNames());
            return "redirect:/";
        }

        redirectAttrs.addAttribute("status", "fail");
        return "redirect:/auth/login?status={status}";
    }

    @GetMapping("/register")
    public String registerView() {

        return "auth/register";
    }

    @PostMapping ("/register")
    public String register (@RequestParam MultiValueMap body) {
        //, RedirectAttributes redirectAttrs, HttpSession session

        String fullName = String.valueOf(body.get("fullName")).replace("[","").replace("]","");
        String email = String.valueOf(body.get("email")).replace("[","").replace("]","");
        String password = String.valueOf(body.get("password")).replace("[","").replace("]","");
        String address = String.valueOf(body.get("address")).replace("[","").replace("]","");
        String city = String.valueOf(body.get("city")).replace("[","").replace("]","");
        String postalCode = String.valueOf(body.get("postalCode")).replace("[","").replace("]","");
        String country = String.valueOf(body.get("country")).replace("[","").replace("]","");
        String phoneNumber = String.valueOf(body.get("phoneNumber")).replace("[","").replace("]","");

        User user = new User(fullName, "", email, address, postalCode, city, country, phoneNumber, password);
        UserRepository.addUser(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/signout")
    public String signoutView(HttpSession session) {
        // Get Request for at logge ud

        session.removeAttribute("personnelNumber");
        session.removeAttribute("name");
        session.removeAttribute("email");
        
        return "root"; // Skal return en redirect til "/" ruten
    }


}
