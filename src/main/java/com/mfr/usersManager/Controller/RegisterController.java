package com.mfr.usersManager.Controller;


import com.mfr.usersManager.DTO.RegisterRequestDTO;
import com.mfr.usersManager.DTO.RegisterResponseDTO;
import com.mfr.usersManager.Entity.User;
import com.mfr.usersManager.Exceptions.InvalidParams;
import com.mfr.usersManager.Exceptions.UserAlreadyExistsException;
import com.mfr.usersManager.Service.UserService;
import com.mfr.usersManager.Util.PasswordUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private PasswordUtil passwordUtil;
    private RegisterResponseDTO registerResponseDTO;

    public RegisterController(UserService userService, RegisterResponseDTO registerResponseDTO, PasswordUtil passwordUtil) {
        this.userService = userService;
        this.registerResponseDTO = registerResponseDTO;
        this.passwordUtil = passwordUtil;
    }

    @PostMapping("/")
    public RegisterResponseDTO registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new InvalidParams();


        User user = mapRegisterRequestToUser(registerRequestDTO);
        user.setPassword(passwordUtil.hashPassword(user.getPassword()));
        user = userService.createUser(user);

        mapUserToRegisterResponse(user);
        return registerResponseDTO;
    }

    private User mapRegisterRequestToUser(RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        return user;
    }

    private void mapUserToRegisterResponse(User user) {
        registerResponseDTO.setId(user.get_id());
    }
}

