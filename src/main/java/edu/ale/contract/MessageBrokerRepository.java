package edu.ale.contract;

import edu.ale.entity.MessageBroker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageBrokerRepository extends JpaRepository<MessageBroker, Integer> {
}
