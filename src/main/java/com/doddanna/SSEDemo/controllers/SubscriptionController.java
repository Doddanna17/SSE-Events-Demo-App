package com.doddanna.SSEDemo.controllers;

import com.doddanna.SSEDemo.models.Subscription;
import com.doddanna.SSEDemo.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public Subscription subscribe(@RequestParam String userId, @RequestParam String eventType) {
        return subscriptionService.subscribe(userId, eventType);
    }

    @DeleteMapping
    public void unsubscribe(@RequestParam String userId, @RequestParam String eventType) {
        subscriptionService.unsubscribe(userId, eventType);
    }
}
