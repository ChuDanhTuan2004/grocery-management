package com.tuancd.grocerystoremanagement.controller;

import com.tuancd.grocerystoremanagement.dto.ResponseDTO;
import com.tuancd.grocerystoremanagement.model.JwtResponse;
import com.tuancd.grocerystoremanagement.model.JwtToken;
import com.tuancd.grocerystoremanagement.model.Role;
import com.tuancd.grocerystoremanagement.model.User;
import com.tuancd.grocerystoremanagement.repository.JwtTokenRepository;
import com.tuancd.grocerystoremanagement.service.RoleService;
import com.tuancd.grocerystoremanagement.service.UserService;
import com.tuancd.grocerystoremanagement.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenRepository jwtTokenRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Iterable<User> users = userService.findAll();
        if (!users.iterator().hasNext()) {
            Role role = roleService.findByName("ROLE_ADMIN");
            user.setRoles(Collections.singletonList(role));
        } else {
            Role role = roleService.findByName("ROLE_USER");
            user.setRoles(Collections.singletonList(role));
        }

        for (User currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>("Email existed", HttpStatus.OK);
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getEmail());

            return ResponseEntity.ok(new ResponseDTO("200", "Login Success",
                    new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities())));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO("401", "Email or password invalid", null));
        }
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<String> logout(@RequestHeader HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
        // Kiểm tra xem authorization có giá trị null không trước khi sử dụng
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            JwtToken jwtToken = jwtTokenRepository.findByTokenEquals(token);

            if (jwtToken != null) {
                jwtToken.setValid(false);
                jwtTokenRepository.save(jwtToken);
                return ResponseEntity.ok("Logout success");
            } else {
                return ResponseEntity.badRequest().body("Invalid token");
            }
        } else {
            return ResponseEntity.badRequest().body("Authorization header missing or invalid");
        }
    }
}
