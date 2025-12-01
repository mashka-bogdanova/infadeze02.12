package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    // curl --location 'localhost:8081/messages'
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }

    //curl --location 'localhost:8081/messages' \
    //--header 'Content-Type: text/plain' \
    //--data '{
    //    йоу
    //}'
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/0'
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl --location --request DELETE 'localhost:8081/messages/0'
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }


    //curl --location --request PUT 'localhost:8081/messages/0' \
    //--header 'Content-Type: text/plain' \
    //--data 'йоу1'
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
}

