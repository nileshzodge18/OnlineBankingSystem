package com.banking.service.entities;

import com.banking.model.entities.User;
import com.banking.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepo userRepo;

    public Optional<User> retrieveUserDetails(Integer uid_user){
      return userRepo.findById(uid_user);
    }

    public List<User> retrieveAllUserDetails(){
        return userRepo.findAll();
    }

    public User saveUserDetails(User user){
        return userRepo.save(user);
    }

    public HttpStatus updateUsers(User user){
        Optional<User> fetchUser = userRepo.findById(user.getUidUsers());
        if(!fetchUser.isEmpty()){
            userRepo.save(user);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus deleteUser(Integer uid_user) {

        try {
            if (!userRepo.findById(uid_user).isEmpty()) {
                userRepo.deleteById(uid_user);
                return HttpStatus.NO_CONTENT;
            } else {
                return HttpStatus.NOT_FOUND;
            }
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

        public HttpStatus deleteAllUsers() {

            try {
                if (!userRepo.findAll().isEmpty()) {
                    userRepo.deleteAll();
                    return HttpStatus.NO_CONTENT;
                } else {
                    return HttpStatus.NOT_FOUND;
                }
            } catch (Exception ex) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

        }


}
