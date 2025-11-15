package com.makerspace.makerspaceapp.controller;



import com.makerspace.makerspaceapp.model.user;
import com.makerspace.makerspaceapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
private final UserService userService;
public UserController(UserService userService) { this.userService = userService; }


@PostMapping("/register")
public ResponseEntity<user> register(@RequestBody user user){
user saved = userService.save(user);
return ResponseEntity.ok(saved);
}


@GetMapping
public List<user> all(){ return userService.getAll(); }


@GetMapping("/{id}")
public ResponseEntity<user> get(@PathVariable Long id){
user u = userService.getById(id);
if(u==null) return ResponseEntity.notFound().build();
return ResponseEntity.ok(u);
}
}