package com.user_service.user_service.controller;

import com.user_service.user_service.dto.UserRequestDTO;
import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    // Create a new user
//    @PostMapping
//    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
//        try {
//            UserResponseDTO createdUser = userService.createUser(userRequestDTO);
//            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get user by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
//        try {
//            UserResponseDTO user = userService.getUserById(id);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Get user by username
//    @GetMapping("/username/{username}")
//    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
//        try {
//            UserResponseDTO user = userService.getUserByUsername(username);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Get user by email
//    @GetMapping("/email/{email}")
//    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
//        try {
//            UserResponseDTO user = userService.getUserByEmail(email);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Get all users
//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
//        try {
//            List<UserResponseDTO> users = userService.getAllUsers();
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Update user
//    @PutMapping("/{id}")
//    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
//        try {
//            UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Delete user
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Check if username exists
//    @GetMapping("/check/username/{username}")
//    public ResponseEntity<Boolean> userExistsByUsername(@PathVariable String username) {
//        try {
//            Boolean exists = userService.userExistsByUsername(username);
//            return new ResponseEntity<>(exists, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Check if email exists
//    @GetMapping("/check/email/{email}")
//    public ResponseEntity<Boolean> userExistsByEmail(@PathVariable String email) {
//        try {
//            Boolean exists = userService.userExistsByEmail(email);
//            return new ResponseEntity<>(exists, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

