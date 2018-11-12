package com.mfr.usersManager.Util;

import com.mongodb.lang.NonNull;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    private static int workload = 12;


    public String hashPassword(String password){
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    public boolean checkPassword(String password, @NonNull String passwordHash)throws Exception{
        if(passwordHash == null|| !passwordHash.startsWith("$2a$")) // otherwise it would raise Invalid salt version exception
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        return BCrypt.checkpw(password, passwordHash);
    }
}
