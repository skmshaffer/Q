package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.raikes.Q.model.Queue;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.model.User;
import school.raikes.Q.service.QueueItemService;
import school.raikes.Q.service.QueueService;
import school.raikes.Q.service.UserService;
import school.raikes.Q.web.FlashMessage;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class QueueController {

    private QueueService queueService;
    private QueueItemService queueItemService;
    private UserService userService;

    @Autowired
    public QueueController(QueueService queueService, QueueItemService queueItemService, UserService userService) {
        this.queueService = queueService;
        this.queueItemService = queueItemService;
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

    @RequestMapping(value = "/queue/{queueCode}/add")
    public String newQueueItemForm(Model model, Principal principal, @PathVariable("queueCode") String queueCode) {
        Queue queue = queueService.findByQueueCode(queueCode);

        if (queue == null) {
            //TODO: Handle this somehow.
        }

        if (principal != null) {
            User currentUser = userService.findByUsername(principal.getName());
            model.addAttribute("user", currentUser);
        }

        model.addAttribute("queueItem", new QueueItem());
        model.addAttribute("queueCode", queueCode);

        return "create_queue_item";
    }

    @RequestMapping(value = "/queue/{queueCode}/add", method = RequestMethod.POST)
public String newQueueItem(@PathVariable("queueCode") String queueCode, @ModelAttribute("queueItem") QueueItem queueItem) {

        queueItem.setQueue(queueService.findByQueueCode(queueCode));
        queueItem.setComplete(false);

        queueItemService.save(queueItem);

        return "redirect:/queue/" + queueCode;
    }


}
