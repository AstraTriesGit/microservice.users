package io.gigachad.microservice.users.services;

import io.gigachad.microservice.users.exceptions.InvalidPhoneNumberException;
import io.gigachad.microservice.users.exceptions.UserNotFoundException;
import io.gigachad.microservice.users.models.MSUser;
import io.gigachad.microservice.users.models.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger("UserService");

    @Autowired
    UserRepo repo;

    public MSUser getUser(int id) {
        logger.info("[getUser()] Fetching details of user with id: {}", id);

        Optional<MSUser> tempUser = repo.findById(id);
        if (tempUser.isEmpty()) {
            logger.error("[getUser()] User with given id not found!: {}", id);
            throw new UserNotFoundException("This user does not exist: " + id);
        }
        logger.info("[getUser()] Fetched details successfully: {}", tempUser.get());
        return repo.save(tempUser.get());
    }


    public MSUser addUser(MSUser MSUser) {
        logger.info("[addUser()] Adding user: {}", MSUser);
        if (!Validators.verifyPhoneNumber(MSUser.getPhoneNumber())) {
             logger.error("[addUser()] Bad phone number found!: {}", MSUser.getPhoneNumber());
             throw new InvalidPhoneNumberException("Bad phone number format: " + MSUser.getPhoneNumber());
        }
        logger.info("[addUser()] Added user successfully: {}", MSUser);
        return repo.save(MSUser);
    }

    @Transactional
    public MSUser patchUser(int id, Map<String, Object> userPatch) {
        logger.info("[patchUser()] Patching user with id {}", id);

        Optional<MSUser> tempUser = repo.findById(id);
        if (tempUser.isPresent()) {
            userPatch.forEach( (key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                Field field = ReflectionUtils.findField(MSUser.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, tempUser.get(), value);
            });
        } else {
            logger.error("[getUser()] User with given id not found!: {}", id);
            throw new UserNotFoundException("This user does not exist: " + id);
        }
        logger.info("[patchUser()] Patched user details with id {}", id);
        return repo.save(tempUser.get());
    }

    @Transactional
    public void deleteUser(int id) {
        logger.info("[deleteUser] Deleting user with id {}", id);

        Optional<MSUser> tempUser = repo.findById(id);
        if (tempUser.isEmpty()) {
            logger.error("[getUser()] User with given id not found!: {}", id);
            throw new UserNotFoundException("This user does not exist: " + id);
        }
        logger.info("[deleteUser] Deleted user with id {}", id);
        repo.delete(tempUser.get());
    }
}
