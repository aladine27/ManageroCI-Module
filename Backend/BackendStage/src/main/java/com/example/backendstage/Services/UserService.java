package com.example.backendstage.Services;

import com.example.backendstage.Entity.user;
import com.example.backendstage.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
   
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userrep;

    public user adduser(user user){
        return userrep.save(user);
    }
    public List<user> getalluser(){
        return userrep.findAll();
    }

}
