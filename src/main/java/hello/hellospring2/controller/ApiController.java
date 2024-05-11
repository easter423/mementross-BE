package hello.hellospring2.controller;

import hello.hellospring2.controller.DTO.GuestSignFormDTO;
import hello.hellospring2.controller.DTO.SignUpFormDTO;
import hello.hellospring2.service.MemberService;
import hello.hellospring2.service.StatusResult;
import hello.hellospring2.service.Result;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Result.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = Result.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PostMapping("/guestsignup")
    public ResponseEntity GuestuserSignup(@RequestBody GuestSignFormDTO formDTO) {
        return memberService.guestsignup(formDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = StatusResult.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PostMapping("/guestsignout")
    public ResponseEntity GuestuserSignout(@RequestBody GuestSignFormDTO formDTO) {
        return memberService.guestsignout(formDTO);
    }

}
