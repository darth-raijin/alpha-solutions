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

    /*
            PROJECTS
    */
    @GetMapping("projects") 
    public String viewProject(Model model, @RequestParam(value = "id", required = true) String id) {
        // Tilføj modelattribute med Subprojects
        model.addAttribute("subprojects", SubProjectService.getSubprojectsByParentId(Integer.parseInt(id)));
        model.addAttribute("project", ProjectService.getProjectById(id));
        System.out.println("ID: " + id);
        return "project/viewproject";
    }

    @GetMapping("deleteproject") 
    public String viewProject(@RequestParam(value = "id", required = true) String id) {
        ProjectService.deleteProject(Integer.parseInt(id));
        return "project/myprojects";
    }

    @GetMapping("updateproject")
    public String viewUpdateProject(Model model, @RequestParam(value = "id", required = true) String id) {
        model.addAttribute("project", ProjectService.getProjectById(id));
        return "project/updateproject";
    }

    @PostMapping("updateProject")
    public String updateProject(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs,  @RequestParam(value = "id", required = true) String id) {
        String name = String.valueOf(body.get("name")).replace("[","").replace("]","");
        String description = String.valueOf(body.get("description")).replace("[","").replace("]","");

        boolean updated = ProjectService.updateProject(name, description, Integer.parseInt(id));
        
        if (updated) {
            redirectAttrs.addAttribute("id", id);
            return "redirect:/myprojects/project?id={id}";
        }

        return "redirect:/myprojects/updateproject?status=fail";
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

        /*
            SUBPROJECTS
         */

    @GetMapping("subproject") 
    public String viewSubproject(Model model, @RequestParam(value = "id", required = true) String id) {
        // Used for viewing a subproject as a 'parent', with associated tasks in cards
        model.addAttribute("subproject", SubProjectService.getSubProjectById(Integer.parseInt(id)));
        model.addAttribute("tasks", TaskService.getTasksByParentId(Integer.parseInt(id)));
        
        return "project/viewsubproject";
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
    public String viewUpdateSubproject(Model model, @RequestParam(value = "id", required = true) String id) {
        model.addAttribute("subproject", SubProjectService.getSubProjectById(Integer.parseInt(id)));
        return "project/updatesubproject";
    }

    @PostMapping("updatesubproject")
    public String updateSubproject(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs,  @RequestParam(value = "id", required = true) String id) {
        String name = String.valueOf(body.get("name")).replace("[","").replace("]","");
        String description = String.valueOf(body.get("description")).replace("[","").replace("]","");

        boolean updated = SubProjectService.updateSubProject(name, description, Integer.parseInt(id));

        if (updated) {
            redirectAttrs.addAttribute("id", id);
            return "redirect:/myprojects/subproject?id={id}";
        }

        return "redirect:/myprojects/updatesubproject?status=fail";
    }

    @GetMapping("deletesubproject")
    public String deleteSubProject(@RequestParam(value = "id", required = true) String id, RedirectAttributes redirectAttrs) {
        int parentId = SubProjectService.deleteSubProject(Integer.parseInt(id));
        if (parentId >= 0) {
            System.out.println("Subproject successfully deleted ID: " + id);

        }
        redirectAttrs.addAttribute("id", parentId);
        return "redirect:/myprojects/projects?id={id}";
    }

    /*
            TASKS
    */

    @GetMapping("tasks") 
    public String viewTasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        System.out.println("ID: " + id);
        return "root";
    }

    @GetMapping("createtask") 
    public String createTask(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs) {
        

        return "root";
    }

        /*
            SUBTASKS
         */

    @GetMapping("viewSubtasks") 
    public String viewSubtasks(Model model, @RequestParam(value = "id", required = true) String id) {
        
        return "root";
    }

    @GetMapping("createSubtask") 
    public String createSubtask(@RequestParam MultiValueMap body, RedirectAttributes redirectAttrs) {
        
        return "root";
    }
}
