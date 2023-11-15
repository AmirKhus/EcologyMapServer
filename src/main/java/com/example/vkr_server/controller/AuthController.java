package com.example.vkr_server.controller;

import com.example.vkr_server.dto.UserAuthDto;
import com.example.vkr_server.model.ERole;
import com.example.vkr_server.model.Role;
import com.example.vkr_server.model.User;
import com.example.vkr_server.payload.request.SignupRequest;
import com.example.vkr_server.payload.response.JwtResponse;
import com.example.vkr_server.payload.response.MessageResponse;
import com.example.vkr_server.repository.RoleRepository;
import com.example.vkr_server.repository.UserRepository;
import com.example.vkr_server.security.jwt.JwtUtils;
import com.example.vkr_server.service.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody UserAuthDto userAuthDto){
        System.out.println(userAuthDto.getEmail() + " " + userAuthDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDto.getEmail(), userAuthDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetailsImplementation.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
            jwt,
                userDetailsImplementation.getId(),
                userDetailsImplementation.getEmail(),
                userDetailsImplementation.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signupRequest){
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signupRequest.getEmail(),
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));
        List<String> strRoles = signupRequest.getRole();
        List<Role> roles= new ArrayList<Role>();
        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role ->{
                switch (role){
                    case "admin":{
                        Role adminRole =roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    }
                    case "mod":{
                        Role moderatorRole =roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(moderatorRole);
                        break;
                    }
                    default:{
                        Role userRole =roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                        break;

                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
