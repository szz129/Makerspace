package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) { 
        this.userService = userService; 
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<User> all(){ 
        return userService.getAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        User u = userService.getById(id);
        if(u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }
}