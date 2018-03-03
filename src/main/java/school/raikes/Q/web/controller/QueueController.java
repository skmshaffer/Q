package school.raikes.Q.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import school.raikes.Q.service.QueueService;

public class QueueController {

    private QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }
}
