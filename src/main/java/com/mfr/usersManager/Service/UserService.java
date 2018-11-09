package com.mfr.usersManager.Service;

import com.mfr.usersManager.Dao.UserDao;
import com.mfr.usersManager.Entity.User;
import com.mfr.usersManager.Exceptions.UserNotFoundForEmailException;
import com.mfr.usersManager.Exceptions.UserNotFoundForIdException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(ObjectId id) throws UserNotFoundForIdException {
        User user = userDao.findBy_id(id);
        if (user == null) {
            throw new UserNotFoundForIdException(id.toHexString());
        }
        return user;
    }

    public User getUserByEmail(String email) throws UserNotFoundForEmailException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundForEmailException(email);
        }
        return user;
    }

    public User createUser(User user) {
        user.set_id(ObjectId.get());
        userDao.save(user);
        return user;
    }

    public User updateUserById(ObjectId id, User user) throws UserNotFoundForIdException {
        getUserById(id); // will throw exception if doesn't
        userDao.save(user);
        return user;
    }

    public User updateUserByEmail(String email, User user) throws UserNotFoundForEmailException {
        getUserByEmail(email); // will throw exception if it doesn't
        userDao.save(user);
        return user;
    }
}
