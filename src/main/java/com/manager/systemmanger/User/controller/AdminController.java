package com.manager.systemmanger.User.controller;


import com.manager.systemmanger.User.req.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/data")
    public String data(@RequestAttribute(name = "user", required = false) User user){
        if(user != null && user.getRole().equals("admin")){
            return "Welcome " + user.getAccountName();
        }
        else{
            return "Access denied";
        }
    }

    private static final Path path = Path.of("userAccess.txt");

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(
            @RequestAttribute(name = "user", required = false) User user,
            @RequestBody Map<String, Object> json){
        if(user != null && user.getRole().equals("admin")){
            try{
                String userId = json.get("userId").toString();
                List<String> resources = (List<String>) json.get("endpoint");
                String record = userId + ": "+ String.join(", ", resources) + "\n";

                Files.writeString(path, record, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                return ResponseEntity.ok("Access granted!");

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("File IO Error!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
            }

        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

    }
}
