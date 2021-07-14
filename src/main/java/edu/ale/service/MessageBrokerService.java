package edu.ale.service;

import edu.ale.contract.MessageBrokerRepository;
import edu.ale.entity.MessageBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageBrokerService {

    @Autowired
    private MessageBrokerRepository repository;

    public List<MessageBroker> listAll(){
        return repository.findAll();
    }

    public void save(MessageBroker messageBroker){
        repository.save(messageBroker);
    }

    public MessageBroker get(Integer id){
        return repository.findById(id).get();
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}
