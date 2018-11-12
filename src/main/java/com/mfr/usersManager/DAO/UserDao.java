package com.mfr.usersManager.DAO;

import com.mfr.usersManager.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends MongoRepository<User, String> {
    User findBy_id(ObjectId objectId);
    User findByEmail(String email);
}
