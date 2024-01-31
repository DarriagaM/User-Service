package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.handler.IRolHandler;
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

import java.util.List;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolRestController {
    private final IRolHandler rolHandler;

    @Operation(summary = "Add new rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Rol Created",content = @Content),
            @ApiResponse(responseCode = "409",description = "Rol already exists",content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveRol(@RequestBody RolRequestDto rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All roles returned",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = RolResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RolResponseDto>> getAllRoles(){
        return ResponseEntity.ok(rolHandler.getAllRol());
    }

    @Operation(summary = "Get rol by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Rol returned",content = @Content),
            @ApiResponse(responseCode = "404",description = "Rol not found",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> getRolById(@PathVariable(value = "id")Long id){
        return ResponseEntity.ok(rolHandler.getRolById(id));
    }

    @Operation(summary = "Delete rol by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Rol delete",content = @Content),
            @ApiResponse(responseCode = "404",description = "Rol not found", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRolById(@PathVariable(value = "id")Long id){
        rolHandler.deleteRolById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
