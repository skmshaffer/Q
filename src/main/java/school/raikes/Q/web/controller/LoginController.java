package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.raikes.Q.model.User;
import school.raikes.Q.service.UserService;
import school.raikes.Q.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());

        try {
            FlashMessage flash = (FlashMessage) request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().getAttribute("flash");
        } catch (Exception e) {
            // Flash is non-existent. Continue as normal.
        }

        return "login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Logged out successfully!", FlashMessage.MessageType.SUCCESS));
        return "redirect:/login";
    }

    @RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    @RequestMapping("/register")
    public String register() {
        return null;
    }



}
