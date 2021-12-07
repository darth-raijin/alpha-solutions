package kea.gruppe5.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kea.gruppe5.project.models.Project;
import kea.gruppe5.project.models.User;
import kea.gruppe5.project.service.AuthService;
import kea.gruppe5.project.service.ProjectService;

@Controller
@RequestMapping("myprojects")
public class ProjectController {

    @GetMapping("/")
    public String myprojects(Model model, HttpSession session) {

        model.addAttribute("projects", ProjectService.getProjectsByUUID
        (String.valueOf(session.getAttribute("personnelNumber"))));

        return "project/myprojects";
    }

    @GetMapping("projects") 
    public String viewProject(Model model, @RequestParam(value = "id", required = true) String id) {
        // Tilføj modelattribute med Subprojects
        model.addAttribute("projects","ssa");
        
        System.out.println("ID: " + id);
        return "project/viewproject";
    }

    @GetMapping("subprojects") 
    public String viewSubproject(Model model, @RequestParam(value = "id", required = true) String id) {
        
        System.out.println("ID: " + id);
        return "root";
    }

    @GetMapping("tasks") 
    public String viewTasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        System.out.println("ID: " + id);
        return "root";
    }

    @GetMapping("viewSubtasks") 
    public String viewSubtasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        System.out.println("ID: " + id);
        return "root";
    }
}
