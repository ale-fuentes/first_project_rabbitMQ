package edu.ale.entity;

import javax.persistence.*;

@Entity
@Table(name = "messagebroker")
public class MessageBroker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message")
    private String message;

    public MessageBroker(){}

    public MessageBroker(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
