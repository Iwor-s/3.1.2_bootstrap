package web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class MainController {
    UserService userService;
    
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("admin")
    public String adminPage() {
        return "redirect:admin/users";
    }
    
    @GetMapping("user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByEmail(principal.getName()));
        return "user";
    }
}
