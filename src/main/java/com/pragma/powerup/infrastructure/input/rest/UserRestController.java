package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = "Add new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Owner Created",content = @Content),
            @ApiResponse(responseCode = "409",description = "Owner already exists",content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody UserRequestDto owner){
        userHandler.saveUser(owner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All users returned",
                    content = @Content(mediaType = "aplicattion/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userHandler.getAllUser());
    }

    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @Operation(summary = "Exists user by id (boolean)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User exists", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("existsUserById/{id}")
    public ResponseEntity<Boolean> existsUserById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userHandler.existsUserById(id));
    }

    @Operation(summary = "Delete user by id (boolean)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User delete", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(value = "id") Long id){
        userHandler.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
