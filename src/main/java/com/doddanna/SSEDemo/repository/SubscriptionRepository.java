package com.doddanna.SSEDemo.repository;

import com.doddanna.SSEDemo.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(String userId);
    List<Subscription> findByEventType(String eventType);
    List<Subscription> findByUserIdAndEventType(String userId, String eventType);
}
