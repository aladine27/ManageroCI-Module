package com.example.backendstage.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stages")
public class Stage {
    @Id
    private String id;
    private String name;
    private boolean active;  // Indicates if the stage is active or not
    private String status;
    private String log;

    // getters and setters
}
