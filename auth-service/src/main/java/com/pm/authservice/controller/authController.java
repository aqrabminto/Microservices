package com.pm.authservice.controller;

import com.pm.authservice.dto.loginRequestDto;
import com.pm.authservice.dto.loginResponsedto;
import com.pm.authservice.service.authService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class authController {

    private final authService authService;

    public authController(authService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<loginResponsedto> login(@RequestBody loginRequestDto loginRequestDto) {

        Optional<String>tokenOptional=authService.authenticate(loginRequestDto);
        if(tokenOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token=tokenOptional.get();
        return ResponseEntity.ok(new loginResponsedto(token));


    }
@Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader) {
//       Authorization: Bearer <token>
//    System.out.println(authHeader.substring(7));
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return authService.validateToken(authHeader.substring(7))?
                ResponseEntity.ok().build():
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
}
