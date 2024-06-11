package com.doddanna.SSEDemo.services;
import com.doddanna.SSEDemo.models.Subscription;
import com.doddanna.SSEDemo.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription subscribe(String userId, String eventType) {
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setEventType(eventType);
        return subscriptionRepository.save(subscription);
    }

    public void unsubscribe(String userId, String eventType) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserIdAndEventType(userId, eventType);
        subscriptionRepository.deleteAll(subscriptions);
    }

    public List<Subscription> getSubscriptionsForEventType(String eventType) {
        return subscriptionRepository.findByEventType(eventType);
    }
}
