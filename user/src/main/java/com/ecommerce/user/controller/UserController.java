package com.ecommerce.user.controller;

import com.ecommerce.user.dtos.UserRequest;
import com.ecommerce.user.dtos.UserResponse;
import com.ecommerce.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController
{

    private final UserService userService;
   // private final Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;

    }

    //@GetMapping("/api/users")
    @RequestMapping(method = RequestMethod.GET) // @ equestMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK); //return userService.fetchAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") String id) {

        log.info("THIS IS INFO LEVEL: {}", id+"GENERAL SYSTEM INFORMATION");
        log.trace("THIS IS TRACE LEVEL-VERY DETAILS LOG");
        log.debug("THIS IS DEBUG LEVEL-DEBUGGING INFORMATION");
        log.warn("THIS IS WARN LEVEL-WARNING MESSAGE");
        log.error("THIS IS ERROR LEVEL-ERROR MESSAGE");


                return userService.fetchSingleUser(id)
                .map(user -> (ResponseEntity<UserResponse>) new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest) {
        userService.AddUsers(userRequest);
        //userService.AddUsers(userRequestDto);
        //return userService.AddUsers(userRequestDto);
        //return new ResponseEntity<>(userService.AddUsers(userRequestDto), HttpStatus.CREATED);
        //return new ResponseEntity<>(HttpStatus.CREATED);
        //return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
        return ResponseEntity.ok("User Created Successfully"); //return "User Created Successfully";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id,
                                             @RequestBody UserRequest updateduser) {
        boolean isUpdated = userService.updateUser(id, updateduser);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        return ResponseEntity.ok("User updated successfully");
    }



}


