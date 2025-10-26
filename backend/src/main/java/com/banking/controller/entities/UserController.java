package com.banking.controller.entities;

import com.banking.model.entities.User;
import com.banking.service.entities.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/retrieveUser/{uid_user}")
    public ResponseEntity<Optional<User>> retrieveUser(@PathVariable Integer uid_user) {
        Optional<User> user = usersService.retrieveUserDetails(uid_user);
        if(!ObjectUtils.isEmpty(user)){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/retrieveUser/retrieveAll")
    public ResponseEntity<List<User>> retrieveUser() {
        List<User> userList = usersService.retrieveAllUserDetails();
        if(!ObjectUtils.isEmpty(userList)){
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> postUser(@RequestBody User user){
        User userResp = usersService.saveUserDetails(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        HttpStatus statusCode = usersService.updateUsers(user);
        return new ResponseEntity<>(statusCode);

    }

    @DeleteMapping("/deleteUser/{uid_user}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer uid_user){
        HttpStatus statusCode = usersService.deleteUser(uid_user);
        return new ResponseEntity<>(statusCode);
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<User> deleteAllUsers(){
        HttpStatus statusCode = usersService.deleteAllUsers();
        return new ResponseEntity<>(statusCode);


    }

}
