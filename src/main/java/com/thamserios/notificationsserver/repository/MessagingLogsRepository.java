package com.thamserios.notificationsserver.repository;

import com.thamserios.notificationsserver.model.MessagingLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessagingLogsRepository extends MongoRepository<MessagingLog,String> {
}
