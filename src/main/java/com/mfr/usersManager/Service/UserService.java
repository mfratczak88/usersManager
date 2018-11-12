package com.mfr.usersManager.Service;

import com.mfr.usersManager.DAO.UserDao;
import com.mfr.usersManager.Entity.User;
import com.mfr.usersManager.Exceptions.UserAlreadyExistsException;
import com.mfr.usersManager.Exceptions.UserNotFoundForEmailException;
import com.mfr.usersManager.Util.PasswordUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordUtil passwordUtil;


    public User getUserByEmail(String email) throws UserNotFoundForEmailException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundForEmailException(email);
        }
        return user;
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        if (doesUserExist(user)) {
            throw new UserAlreadyExistsException();
        }
        user.set_id(ObjectId.get());
        userDao.save(user);
        return user;
    }

    private boolean doesUserExist(User user) {
        try {
            getUserByEmail(user.getEmail());
            return true;
        } catch (UserNotFoundForEmailException e) {
            return false;
        }
    }

}