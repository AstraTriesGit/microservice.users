package io.gigachad.microservice.users.controllers;

import io.gigachad.microservice.users.contracts.PanInfo;
import io.gigachad.microservice.users.models.CompleteUser;
import io.gigachad.microservice.users.models.MSUser;
import io.gigachad.microservice.users.services.UserPanService;
import io.gigachad.microservice.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger("UserController");

    @Autowired
    UserService service;
    @Autowired
    UserPanService panService;

    @GetMapping(path = "/")
    public String welcome() {
        logger.info("[welcome()] Just a simple echo.");

        return "io.gigachad.microservice.users is up.";
    }

    @GetMapping(path = "/user/{id}")
    public CompleteUser getUser(@PathVariable int id) {
        logger.info("[getUser()] Fetching user details with id {}", id);

        MSUser user = service.getUser(id);
        PanInfo info = panService.getPanInfo(id);
        return new CompleteUser(user, info.getPanNumber());
    }

    @PostMapping(path = "/add")
    public CompleteUser addUser(@RequestBody CompleteUser completeUser) {
        logger.info("[addUser()] Adding the following details: {}", completeUser);

        MSUser user = service.addUser(completeUser.getUser());
        PanInfo info = panService.addPanInfo(
                new PanInfo(completeUser.getUser().getUserId(), completeUser.getPanNumber())
        );
        return new CompleteUser(user, info.getPanNumber());
    }

    @PatchMapping(path = "/user/{id}")
    public CompleteUser patchUser(@PathVariable int id, @RequestBody Map<String, Object> userPatch) {
        // TODO this dumb ass shit
        return new CompleteUser();
    }

    @DeleteMapping(path = "/user/{id}")
    public String deleteUser(@PathVariable int id) {
        logger.info("[deleteUser()] Deleting the user with the id: {}", id);

        service.deleteUser(id);
        return "Well, you've done it.";
    }
}
