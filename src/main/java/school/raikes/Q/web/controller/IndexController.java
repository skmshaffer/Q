package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import school.raikes.Q.model.User;
import school.raikes.Q.service.UserService;
import school.raikes.Q.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class IndexController {

    private UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(Model model, Principal principal, HttpServletRequest request) {

        try {
            FlashMessage flash = (FlashMessage) request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().removeAttribute("flash");
        } catch (Exception e) {
            // Flash is non-existent. Continue as normal.
        }

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
        }

        return "index";
    }

}
