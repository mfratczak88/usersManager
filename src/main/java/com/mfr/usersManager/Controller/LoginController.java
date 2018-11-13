package com.mfr.usersManager.Controller;


import com.mfr.usersManager.DTO.LoginRequestDTO;
import com.mfr.usersManager.DTO.LoginResponseDTO;
import com.mfr.usersManager.Entity.User;
import com.mfr.usersManager.Exceptions.EmailOrPasswordMismatchException;
import com.mfr.usersManager.Exceptions.InvalidParams;
import com.mfr.usersManager.Service.UserService;
import com.mfr.usersManager.Util.PasswordUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;
    private PasswordUtil passwordUtil;
    private LoginResponseDTO loginResponseDTO;

    public LoginController(UserService userService, PasswordUtil passwordUtil, LoginResponseDTO loginResponseDTO) {
        this.userService = userService;
        this.passwordUtil = passwordUtil;
        this.loginResponseDTO = loginResponseDTO;
    }

    @PostMapping("/")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            throw new InvalidParams();

        User requestUser = mapLoginRequestToUser(loginRequestDTO);
        User dbUser = userService.getUserByEmail(requestUser.getEmail());

        if (!doesPasswordMatch(requestUser.getPassword(), dbUser.getPassword()))
            throw new EmailOrPasswordMismatchException();

        mapUserToLoginResponse(dbUser);
        return loginResponseDTO;
    }

    private User mapLoginRequestToUser(LoginRequestDTO loginRequestDTO) {
        User user = new User();
        user.setEmail(loginRequestDTO.getEmail());
        user.setPassword(loginRequestDTO.getPassword());
        return user;
    }

    private void mapUserToLoginResponse(User user) {
        loginResponseDTO.setId(user.get_id());
        loginResponseDTO.setEmail(user.getEmail());
        loginResponseDTO.setName(user.getName());

    }

    private boolean doesPasswordMatch(String password, String passwordHash){
        try{
            return passwordUtil.checkPassword(password,passwordHash);
        } catch (Exception e){
            return false;
        }
    }
}
