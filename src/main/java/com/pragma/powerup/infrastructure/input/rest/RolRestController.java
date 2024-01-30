package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.handler.IRolHandler;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolRestController {
    private final IRolHandler rolHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveRol(@RequestBody RolRequestDto rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
