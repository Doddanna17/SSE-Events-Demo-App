package com.doddanna.SSEDemo.controllers;


import com.doddanna.SSEDemo.services.EventNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/events")
public class EventNotificationController {

    @Autowired
    private EventNotificationService eventNotificationService;

    @GetMapping(path = "/subscribe",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam String userId, @RequestParam String eventType) {
        return eventNotificationService.subscribe(userId,eventType);
    }

    @PostMapping
    public ResponseEntity<String> sendEvent(@RequestParam String message, @RequestParam String eventType) {
        eventNotificationService.sendEvent(eventType,message);
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }


}
