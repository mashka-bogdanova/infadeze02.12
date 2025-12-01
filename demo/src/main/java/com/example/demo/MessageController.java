package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    // curl --location 'localhost:8081/messages/mySubstring/yiou'
    @GetMapping("messages/mySubstring/{substring}")
    public ResponseEntity<List<String>> getMessages(@PathVariable String substring) {
        List<String> messages_new = new ArrayList<>();
        for(String s: messages){
            if(s.contains(substring)){
                messages_new.add(s);

            }
        }
        return ResponseEntity.ok(messages_new);
    }

    //curl --location 'localhost:8081/messages' \
    //--header 'Content-Type: application/json' \
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
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl --location --request DELETE 'localhost:8081/messages/0'
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }


    //curl --location --request PUT 'localhost:8081/messages/0' \
    //--header 'Content-Type: application/json' \
    //--data 'йоу1'
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/search/yiou'
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> getNewIndex(@PathVariable String text) {
        List<String> messages_new = new ArrayList<>();
        int ind = 0;
        for(String s: messages){
            if(s.equals(text)){
                return ResponseEntity.ok(ind);
            }
            ind++;
        }
        return ResponseEntity.ok(-1);
    }

    //curl --location 'localhost:8081/messages/count'
    @GetMapping("messages/count")
    public ResponseEntity<Integer> counter(){
        return ResponseEntity.ok(messages.size());
    }

    //curl --location 'localhost:8081/messages/0/create' \
    //--header 'Content-Type: application/json' \
    //--data 'yiou'
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> createNewMessage(@PathVariable int index, @RequestBody String text){
        messages.add(index, text);
        return ResponseEntity.noContent().build();

    }

    //curl --location --request DELETE 'localhost:8081/messages/search/yiou'
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteNewText(@PathVariable String text) {
        int ind = 0;
        for(String s: messages){
            if(s.contains(text)){
                messages.remove(ind);
            }
            ind++;
        }
        return ResponseEntity.noContent().build();
    }







}

