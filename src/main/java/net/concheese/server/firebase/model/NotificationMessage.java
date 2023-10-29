package net.concheese.server.firebase.model;

import lombok.Data;

import java.util.Map;

@Data
public class NotificationMessage {
    private String recipientToken;
    //private String title;
    //private String body;
    private Map<String, String> data;

}
