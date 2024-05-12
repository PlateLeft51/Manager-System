package com.manager.systemmanger.User.controller;

import com.manager.systemmanger.User.req.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Path path = Path.of("userAccess.txt");

    @GetMapping("/{resource}")
    public String checkResource(@RequestAttribute(name = "user", required = false) User user,
                                @PathVariable String resource){
        if(user != null && user.getRole() != null){
            try (Stream<String> lines = Files.lines(path)){

                Optional<String> accessLine = lines
                        .filter(line -> line.startsWith(user.getUserId() + ":"))
                        .findFirst();

                if (accessLine.isPresent()) {
                    List<String> resources = Arrays.asList(accessLine.get().split(": ")[1].split(", "));
                    if (resources.contains(resource)) {
                        return "Success";
                    } else {
                        return "Failure";
                    }
                } else {
                    return "Failure";
                }
            } catch(Exception e){
                return "Server error: " + e.getMessage();
            }
        }
        else{
            return "Invalid request";
        }

    }
}
