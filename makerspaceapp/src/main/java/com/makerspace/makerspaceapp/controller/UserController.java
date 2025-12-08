package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User registered", saved));
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> all() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Users fetched", userService.getAll())
        );
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> get(@PathVariable Long id) {
        User u = userService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User fetched", u));
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser
    ) {
        User u = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated", u));
    }

    // UPDATE USER ROLE
    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse<User>> updateRole(
            @PathVariable Long id,
            @RequestParam String role
    ) {
        User updated = userService.updateUserRole(id, role);
        return ResponseEntity.ok(new ApiResponse<>(true, "Role updated", updated));
    }
}
