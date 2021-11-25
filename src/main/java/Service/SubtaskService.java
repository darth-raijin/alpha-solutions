package Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class SubtaskService {
    @PostMapping ("/createsubtask")
    public void createSubtask (@RequestParam String name,
                               @RequestParam String description,
                               @RequestParam ArrayList<Integer> assignedWorkers) {

    }

    @PostMapping ("/updatesubtask")
    public void updateSubtask (@RequestParam String name,
                               @RequestParam String description,
                               @RequestParam ArrayList<Integer> assignedWorkers) {

    }
}
