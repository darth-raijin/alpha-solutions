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
import kea.gruppe5.project.repository.SubprojectRepository;
import kea.gruppe5.project.service.AuthService;
import kea.gruppe5.project.service.ProjectService;
import kea.gruppe5.project.service.SubProjectService;
import kea.gruppe5.project.service.TaskService;

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
        model.addAttribute("subprojects", SubProjectService.getSubprojectsByParentId(Integer.parseInt(id)));
        model.addAttribute("project", ProjectService.getProjectById(id));
        System.out.println("ID: " + id);
        return "project/viewproject";
    }

    @GetMapping("updateproject")
    public String viewUpdateProject(@RequestParam(value = "id", required = true) String id) {
        return "project/updateproject";
    }

    @GetMapping("createproject") 
    public String createProjectView() {
        return "project/createproject";
    }

    @PostMapping("createproject")
    public String createProject(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs, HttpSession session) {
        String name = String.valueOf(body.get("name")).replace("[","").replace("]","");
        String description = String.valueOf(body.get("description")).replace("[","").replace("]","");
        String personnelNumber = (String) session.getAttribute("personnelNumber");

        // Hvis en model bliver returneret fra ProjectService, bliver den brugt til redirect
        int createdProjectId = ProjectService.createProject(name, description, personnelNumber);

        if (createdProjectId >= 0) {
            redirectAttrs.addAttribute("id", createdProjectId);
            return "redirect:/myprojects/projects?id={id}";
        }
        // Handle fail aka hvis createdProjectId er -1
        return "redirect:/myprojects/createproject?status=fail";
    }

    @GetMapping("subprojects") 
    public String viewSubproject(Model model, @RequestParam(value = "id", required = true) String id) {
        // Used for viewing a subproject as a 'parent', with associated tasks in cards
        model.addAttribute("subproject", SubProjectService.getSubProjectById(Integer.parseInt(id)));
        model.addAttribute("tasks", TaskService.getTasksByParentId(Integer.parseInt(id)));
        
        return "root";
    }

    @PostMapping("createsubproject") 
    public String createSubproject(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs,  @RequestParam(value = "id", required = true) String id) {
        // Det ID der bliver modtaget er ID for ejeren. I dette tilfælde hvilket projekt det hører under
        
        String name = String.valueOf(body.get("name")).replace("[","").replace("]","");
        String description = String.valueOf(body.get("description")).replace("[","").replace("]","");
        
        int creationResult = SubProjectService.createSubproject(name, description, id);

        if (creationResult >= 0) {
            redirectAttrs.addAttribute("id", creationResult);
            return "redirect:/myprojects/subprojects?id={id}";
        }
        
        return "redirect:/myprojects/createsubproject?status=fail";
    }

    @GetMapping("updatesubproject")
    public String viewUpdateSubproject(@RequestParam(value = "id", required = true) String id) {
        return "project/updatesubproject";
    }



    @GetMapping("tasks") 
    public String viewTasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        System.out.println("ID: " + id);
        return "root";
    }

    @GetMapping("createtask") 
    public String createTask(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs) {
        

        return "root";
    }

    @GetMapping("viewSubtasks") 
    public String viewSubtasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        return "root";
    }

    @GetMapping("createSubtask") 
    public String createSubtask(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs) {
        
        return "root";
    }
}
