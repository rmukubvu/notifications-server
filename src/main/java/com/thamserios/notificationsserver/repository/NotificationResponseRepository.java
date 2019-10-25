package com.thamserios.notificationsserver.repository;

import com.thamserios.notificationsserver.model.NotificationResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationResponseRepository extends MongoRepository<NotificationResponse,String> {
}
