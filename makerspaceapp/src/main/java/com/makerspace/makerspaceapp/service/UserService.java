package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Role;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.RoleRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service annotation tells Spring this is a service layer bean
//Spring will create a singleton object of this class
//It can be injected into controllers
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    //These are dependencies
    // Marked final so they must be assigned in constructor
    // They provide required functionality
    //Dependency Injection (DI): Spring automatically provides objects to classes so you don’t manually create new objects.
    
    //Constructor Injection
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Spring auto-injects these repositories and password encoder into service
    
    // CREATE USER
    @Transactional
    public User save(User user) { //Marks method as a DB transaction
                                 // If anything fails -> rollback
                                //Method accepts a User object

        //Encode password
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));//PasswordEncoder hashes passwords so stolen DB data is useless.
        }

        // Handle role
        if (user.getRole() != null && user.getRole().getName() != null) {
            Role role = roleRepository.findByName(user.getRole().getName())
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", user.getRole().getName()));
            user.setRole(role);
        }
        /*If user has a role object with a role name
        *Look up role in DB by name
        *If not found → throw exception
        *Otherwise set the existing role on user

        Theory:
        *We dont store random roles;
        *Only roles existing in DB are allowed (data integrity). 
        */

        return userRepository.save(user);
    }
    //.save() is a JPA repository method for insert/update.

    // READ
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    // UPDATE USER (full update)
    @Transactional
    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }

        if (updatedUser.getSkills() != null) {
            existingUser.setSkills(updatedUser.getSkills());
        }

        // Update password
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Update role (if provided)
        if (updatedUser.getRole() != null && updatedUser.getRole().getName() != null) {
            Role role = roleRepository.findByName(updatedUser.getRole().getName())
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", updatedUser.getRole().getName()));
            existingUser.setRole(role);
        }

        return userRepository.save(existingUser);
    }

    // ===========================
    // UPDATE USER ROLE ONLY
    // ===========================
    @Transactional
    public User updateUserRole(Long id, String roleName) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName));

        user.setRole(role);

        return userRepository.save(user);
    }

    // ===========================
    // DELETE USER
    // ===========================
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
}
