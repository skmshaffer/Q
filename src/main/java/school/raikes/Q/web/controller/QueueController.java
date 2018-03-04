package school.raikes.Q.web.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.User;
import school.raikes.Q.service.QueueService;
import school.raikes.Q.service.UserService;
import school.raikes.Q.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class QueueController {

    private QueueService queueService;
    private UserService userService;

    @Autowired
    public QueueController(QueueService queueService, UserService userService) {
        this.queueService = queueService;
        this.userService = userService;
    }

    @RequestMapping(value = "/queue", method = RequestMethod.POST)
    public String findQueue(HttpServletRequest request, @RequestParam("queue-code") String queueCode) {
        Queue queue = queueService.findByQueueCode(queueCode);

        if (queue != null) {
            return "redirect:/queue/" + queueCode;
        } else {
            FlashMessage message = new FlashMessage("Unable to find a queue with that Queue Code. Please Try Again.", FlashMessage.MessageType.SUCCESS);
            request.getSession().setAttribute("flash", message);

            return "redirect:/";
        }
    }

    @RequestMapping(value = "/queue/{queueCode}", method = RequestMethod.GET)
    public String viewQueue(Model model, Principal principal, @PathVariable("queueCode") String queueCode) {
        Queue queue = queueService.findByQueueCode(queueCode);

        if (queue == null) {
            //TODO: Handle this somehow.
        }

        if (principal != null) {
            User currentUser = userService.findByUsername(principal.getName());
            model.addAttribute("user", currentUser);
        }

        model.addAttribute("queue", queue);

        return "queue";
    }


}
