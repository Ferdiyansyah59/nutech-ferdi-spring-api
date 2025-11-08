package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.LoginRequestDTO;
import com.nutech_ferdi.nutech_ferdi.dto.RegisterRequestDTO;
import com.nutech_ferdi.nutech_ferdi.model.User;

public interface AuthService {
    String login(LoginRequestDTO loginRequest);

    User register(RegisterRequestDTO registerRequest);
}
