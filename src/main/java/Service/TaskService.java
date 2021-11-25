package Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class TaskService {
    @PostMapping ("/updatetask")
    public void updateTask (@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam ArrayList<Integer> assignedWorkers,
                            @RequestParam double time) {

    }
    @PostMapping ("/createtask")
    public void createTask (@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam ArrayList<Integer> assignedWorkers,
                            @RequestParam double time) {

    }
}
