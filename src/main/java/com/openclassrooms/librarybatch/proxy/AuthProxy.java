package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.JwtResponse;
import com.openclassrooms.librarybatch.model.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8080/api/auth", value = "auth-api")
public interface AuthProxy {

    @PostMapping("/signin")
    ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest);
}
