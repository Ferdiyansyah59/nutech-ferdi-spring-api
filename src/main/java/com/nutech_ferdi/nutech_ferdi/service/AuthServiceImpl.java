package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.LoginRequestDTO;
import com.nutech_ferdi.nutech_ferdi.dto.RegisterRequestDTO;
import com.nutech_ferdi.nutech_ferdi.exception.ResourceAlreadyExistException;
import com.nutech_ferdi.nutech_ferdi.model.User;
import com.nutech_ferdi.nutech_ferdi.repository.UserRepository;
import com.nutech_ferdi.nutech_ferdi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passEncod;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginRequestDTO loginRequest) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtTokenProvider.generateToken(auth);
    }

    @Override
    public User register(RegisterRequestDTO registerRequest) {

        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new ResourceAlreadyExistException("email " + registerRequest.getEmail() + " is already exist!");
        }

        User user = new User();

        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirst_name());
        user.setLastName(registerRequest.getLast_name());
        user.setPassword(passEncod.encode(registerRequest.getPassword()));

        return userRepo.save(user);
    }
}
