package ruan.eloy.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ruan.eloy.backend.entity.Student;
import ruan.eloy.backend.dto.ApiResponse;
import ruan.eloy.backend.dto.JwtAuthenticationResponse;
import ruan.eloy.backend.dto.SignInRequest;
import ruan.eloy.backend.dto.SignUpRequest;
import ruan.eloy.backend.service.StudentService;

import javax.validation.Valid;
import java.net.URI;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private StudentService studentService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                       StudentService studentService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> authenticate(@Valid SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getRegistration(),
                        signInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToke(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    public ResponseEntity<?> registration(@Valid SignUpRequest signUpRequest) {
        ApiResponse apiResponse;

        if(studentService.isRegistrationUnique(signUpRequest.getRegistration())) {
            apiResponse = new ApiResponse(false, "Registration already taken");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        if(studentService.isEmailUnique(signUpRequest.getEmail())) {
            apiResponse = new ApiResponse(false, "Email already taken");
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Student student = studentService.create(
                signUpRequest.getRegistration(),
                signUpRequest.getCourseCode(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encodedPassword,
                signUpRequest.getPhone()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/student/{registration}")
                .buildAndExpand(student.getRegistration()).toUri();
        apiResponse = new ApiResponse(true, "Student registered successfully");
        return ResponseEntity.created(location).body(apiResponse);
    }
}
