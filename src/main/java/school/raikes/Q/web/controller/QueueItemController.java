package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import school.raikes.Q.model.QueueItem;
import school.raikes.Q.service.QueueItemService;
import school.raikes.Q.service.UserService;


import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class QueueItemController {

    private UserService userService;
    private QueueItemService queueItemService;

    @Autowired
    public QueueItemController(UserService userService, QueueItemService queueItemService) {
        this.userService = userService;
        this.queueItemService = queueItemService;
    }

    @RequestMapping(value = "/queueitem/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateQueueItem(@PathVariable String id, HttpServletResponse response, @RequestBody QueueItem item) {
        queueItemService.save(item);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/queueitem/{id}", method = RequestMethod.GET)
    public ResponseEntity<QueueItem> getItem(Principal principal, HttpServletResponse response, @PathVariable("id") Long id) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        QueueItem item = queueItemService.findById(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
