package me.barbaraDev.controller;

import me.barbaraDev.domain.model.User;
import me.barbaraDev.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAll (){
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);

    }

    @PutMapping("/{id}")
    public ResponseEntity <User> update(@PathVariable Long id, @RequestBody User userToUpdate){
        User updateUser = userService.update(id, userToUpdate);
        return ResponseEntity.ok(updateUser);
    }
}
