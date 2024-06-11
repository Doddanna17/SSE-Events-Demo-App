package com.doddanna.SSEDemo.services;

import com.doddanna.SSEDemo.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventNotificationService {

    private final ConcurrentHashMap<Long, SseEmitter> clients = new ConcurrentHashMap<>();

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Autowired
    SubscriptionService subscriptionService;

    public SseEmitter subscribe(String userId, String eventType)  {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        Subscription subscribe = subscriptionService.subscribe(userId, eventType);
        clients.put(subscribe.getId(), emitter);
        emitter.onCompletion(() -> clients.remove(subscribe.getId()));
        emitter.onTimeout(() -> clients.remove(subscribe.getId()));
        emitter.onError((e) -> clients.remove(subscribe.getId()));
        threadPoolTaskExecutor.execute(()->{
            try {
                emitter.send(SseEmitter.event().name("INIT").data("Subscribed successfully!").id(UUID.randomUUID().toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return emitter;
    }

    public void sendEvent(String eventType, String message) {
        List<Subscription> subscriptionsForEventType = subscriptionService.getSubscriptionsForEventType(eventType);
        subscriptionsForEventType.forEach(subscription -> {
            SseEmitter sseEmitter = clients.get(subscription.getId());
            if(sseEmitter!=null) {
                threadPoolTaskExecutor.execute(()->{
                    try {
                        sseEmitter.send(SseEmitter.event().name(eventType).id(UUID.randomUUID().toString()).data(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }
}
