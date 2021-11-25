package Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class ProjectService {
    @PostMapping("/createproject")
    public void createProject (@RequestParam String name,
                               @RequestParam String description){

    }

    @PostMapping ("/updateproject")
    public void updateProject (@RequestParam String name,
                               @RequestParam String decription,
                               @RequestParam ArrayList<Integer> workers,
                               @RequestParam int projectID) {

    }
}
