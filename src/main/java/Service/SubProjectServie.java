package Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class SubProjectServie {

    @PostMapping ("/udatesub")
    public void updateSubProject (@RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam ArrayList<Integer> assignedWorkers){

    }

    @PostMapping ("/createsub")
        public void createSubProject (@RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam ArrayList<Integer> assignedWorkers){

        }
}
