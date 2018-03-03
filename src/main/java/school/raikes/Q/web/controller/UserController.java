package school.raikes.Q.web.controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.raikes.Q.model.User;
import school.raikes.Q.service.SecurityService;
import school.raikes.Q.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping("/account")
    public String modifyUser(String username, Model model, Principal principal) {

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
        }

        return "account";
    }

    @RequestMapping(value = "/account/profile-picture", method = RequestMethod.POST)
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes) {

        User user = userService.findByUsername(principal.getName());

        try {
            user.setImageType(file.getContentType());
            user.setImage(file.getBytes());

            userService.save(user);
        } catch (IOException ioe) {
            //TODO: Handle this somehow.
        }

        return "redirect:/account";
    }

    @RequestMapping(value = "/account/profile-picture", method = RequestMethod.GET)
    public void getProfilePicture(Principal principal, HttpServletResponse response) {
        try {
            User user = userService.findByUsername(principal.getName());

            response.setContentType(user.getImageType());

            System.out.println(user.getImageType());

            response.getOutputStream().write(user.getImage());
        } catch (IOException ioe) {
            System.out.println(ioe);
            //TODO: Handle this somehow.
        }
    }
}
