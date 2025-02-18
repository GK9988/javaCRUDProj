package com.garvk.CrudProj.services;

import com.garvk.CrudProj.models.User;
import com.garvk.CrudProj.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public User addUser(User aInUser) {
        aInUser.setPassword(bCryptPasswordEncoder.encode(aInUser.getPassword()));
        return userRepo.save(aInUser);
    }
}
