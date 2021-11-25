package Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthService {
    @PostMapping ("/login")
    public void login (@RequestParam String email,
                       @RequestParam String password) {

    }

    @PostMapping ("/register")
    public void register (@RequestParam String name,
                          @RequestParam String password) {

    }

    @PostMapping ("/signout")
    public void signOut(){

    }


}


// Post vi får noget fra brugeren vi skal bruge - brugeren "poster" noget
// Get vi giver noget til brugeren