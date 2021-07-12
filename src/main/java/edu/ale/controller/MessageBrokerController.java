package edu.ale.controller;

import edu.ale.entity.MessageBroker;
import edu.ale.service.MessageBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageBrokerController {

    @Autowired
    private MessageBrokerService service;

    @GetMapping("/messages")
    public List<MessageBroker> list(){
        return service.listAll();
    }

    @PostMapping("/messages")
    public void add(@RequestBody MessageBroker messageBroker){
        service.save(messageBroker);
    }

    @GetMapping(value = "/")
    public String viewHomePage(Model model) {
        List<MessageBroker> listMessageBroker = service.listAll();
        model.addAttribute("listMessageBroker", listMessageBroker);

        return "index";
    }
    @GetMapping("/new")
    public String viewNewMessageBroker(Model model){
        MessageBroker messageBroker = new MessageBroker();
        model.addAttribute("messageBroker", messageBroker);

        return "new_messagebroker";
    }
}
