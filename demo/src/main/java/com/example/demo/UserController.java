package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

public class UserController {

    private final List<User> userList = new ArrayList<>();


    //curl --location 'localhost:8081/user' \
    //--header 'Content-Type: application/json' \
    //--data '"name":"mashka",
    //"age":17'
    @PostMapping("/user")
    public ResponseEntity<List<User>> addUser(@RequestBody User user) {
    userList.add(user);
    return ResponseEntity.ok(userList);
    }

    //curl --location --request DELETE 'localhost:8081/user/0'
    @DeleteMapping("/user/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable int index) {
        userList.remove(index);
        return ResponseEntity.noContent().build();
    }

    //curl --location 'localhost:8081/api/user/0'
    @GetMapping("/user/{index}")
    public ResponseEntity<User> getUser(@PathVariable int index) {
        return ResponseEntity.ok(userList.get(index));
    }

    //curl --location 'localhost:8081/api/users'
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userList);
    }

    //curl --location --request PUT 'localhost:8081/api/user/0' \
    //--header 'Content-Type: application/json' \
    //--data '{
    //    "name":"mashka",
    //    "age":17
    //}'
    @PutMapping("/user/{index}")
    public ResponseEntity<User> updateUser(@PathVariable int index, @RequestBody User user) {
        userList.set(index, user);
        return ResponseEntity.ok(user);
    }


}
