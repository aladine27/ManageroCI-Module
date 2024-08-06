package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.user;
import com.example.backendstage.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {


        private UserService serve;
        @PostMapping("/addEtudiant")
        public user adduser(@RequestBody user e){
            return serve.adduser(e);
        }

        @GetMapping("/getAllEtudiant")
        public List<user> getAllEtudiant (){
            return serve.getalluser();
        }

    }


