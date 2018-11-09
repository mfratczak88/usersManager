package com.mfr.usersManager.Controller;

import com.mfr.usersManager.Entity.User;
import com.mfr.usersManager.Dao.UserDao;
import com.mfr.usersManager.Exceptions.UserNotFoundForIdException;
import com.mfr.usersManager.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Collection<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public User createNewUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@Valid @PathVariable("id") ObjectId id, @Valid User user) throws UserNotFoundForIdException {
        return userService.updateUserById(id, user);
    }

}