package com.sirius.challenge.backend.documentation;

import com.sirius.challenge.backend.dtos.CurrentUserDto;
import com.sirius.challenge.backend.dtos.UserStatsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@Tag(name = "User", description = "Get users stats and current user information")
public interface IUserController {

    @Operation(summary = "Get users stats", description = "Get all users that sent emails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "This operation was successful", content = {
                    @Content(schema = @Schema(implementation = UserStatsDto.class))}),
            @ApiResponse(responseCode = "204", description = "The list is empty", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Access denied, needed authorization to access",
                    content = {@Content})
    })
    ResponseEntity<?> getStats();

}
