package com.sirius.challenge.backend.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Tag(name = "Email", description = "Email service controller")
public interface IEmailController {

    @Operation(summary = "Send email", description = "Allows to an authenticated user to send email through a email service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email was sent", content = @Content),
            @ApiResponse(responseCode = "403", description = "Email limit reached", content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied, needed authorization to access",
                    content = @Content)
    })
    ResponseEntity<?> sendEmail(
            @RequestParam String toEmail, @RequestParam String subject, @RequestParam String body) throws IOException;
}
