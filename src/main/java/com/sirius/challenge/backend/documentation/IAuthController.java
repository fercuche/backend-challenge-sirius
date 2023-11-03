package com.sirius.challenge.backend.documentation;

import com.sirius.challenge.backend.dtos.UserStatsDto;
import com.sirius.challenge.backend.security.models.AuthRequest;
import com.sirius.challenge.backend.security.models.AuthResponse;
import com.sirius.challenge.backend.security.models.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Register and Login layer")
public interface IAuthController {

    @Operation(summary = "Register", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was registered successfully", content = {
                    @Content(schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "403", description = "Missing Params", content = @Content)
    })
    ResponseEntity<?> register(@RequestBody RegisterRequest request);

    @Operation(summary = "Login", description = "Allows to a registered user to authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",  content = {
                    @Content(schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "403", description = "Bad credentials", content = @Content)
    })
    ResponseEntity<?> login(@RequestBody AuthRequest request);
}
