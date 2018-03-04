package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.User;
import school.raikes.Q.service.QueueService;
import school.raikes.Q.service.SecurityService;
import school.raikes.Q.service.UserService;
import school.raikes.Q.utility.ImageUtility;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final QueueService queueService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, QueueService queueService) {
        this.userService = userService;
        this.securityService = securityService;
        this.queueService = queueService;
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
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        try {
            user.setImageType("image/jpeg");

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            byte[] userImage = ImageUtility.prepareProfileImageForStorage(image);

            user.setImage(userImage);

            userService.save(user);
        } catch (IOException ioe) {
            //TODO: Handle this somehow.
        }

        return "redirect:/account";
    }

    @RequestMapping(value = "/account/profile-picture", method = RequestMethod.GET)
    public void getProfilePictureOfSelf(Principal principal, HttpServletResponse response) {
        try {
            User user = userService.findByUsername(principal.getName());

            response.setContentType(user.getImageType());

            response.getOutputStream().write(user.getImage());
        } catch (IOException ioe) {
            System.out.println(ioe);
            //TODO: Handle this somehow.
        }
    }

    @RequestMapping(value = "/account/profile-picture/{username}", method = RequestMethod.GET)
    public void getProfilePictureOfUsername(@PathVariable String username, HttpServletResponse response) {

        try {
            User user = (User) userService.loadUserByUsername(username);

            response.setContentType(user.getImageType());

            response.getOutputStream().write(user.getImage());
        } catch (UsernameNotFoundException unfe) {
            try {
                response.sendError(404, "User not found.");
            } catch (IOException ioe) {

            }
        } catch (IOException ioe) {
            //TODO: Handle this somehow.
        }
    }

    @RequestMapping(value = "/account/queues", method = RequestMethod.GET)
    public String getAllUserQueues(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        List<Queue> queues = queueService.findByOwner(user);
        model.addAttribute("queues", queues);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        model.addAttribute("dateFormat", dateFormat);

        return "my_queues";
    }
}
