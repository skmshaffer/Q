package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import school.raikes.Q.model.User;
import school.raikes.Q.model.dto.RegistrationInfo;
import school.raikes.Q.service.SecurityService;
import school.raikes.Q.service.exceptions.UserRegistrationException;
import school.raikes.Q.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {

    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationController(SecurityService securityService, AuthenticationManager authenticationManager) {
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model, HttpServletRequest request) {

        try {
            FlashMessage flash = (FlashMessage) request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);
            request.getSession().removeAttribute("flash");
        } catch (Exception e) {
            // Flash is non-existent. Continue as normal.
        }

        model.addAttribute("registrationInfo", new RegistrationInfo());

        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("registrationInfo") RegistrationInfo info, BindingResult result, HttpServletRequest request, HttpServletResponse response) {

        try {
            User newUser = securityService.registerNewUser(info);

            authenticateUserAndSetSession(info, request);

            FlashMessage message = new FlashMessage("Account Created Successfully! Welcome to Q.", FlashMessage.MessageType.SUCCESS);
            request.getSession().setAttribute("flash", message);

            return "redirect:/";
        } catch (UserRegistrationException ure) {
            FlashMessage message = new FlashMessage(ure.getMessage(), FlashMessage.MessageType.FAILURE);
            request.getSession().setAttribute("flash", message);
            return "redirect:/register";
        }
    }

    private void authenticateUserAndSetSession(RegistrationInfo info, HttpServletRequest request) {
        String username = info.getUsername();
        String password = info.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticaticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticaticatedUser);
    }
}
