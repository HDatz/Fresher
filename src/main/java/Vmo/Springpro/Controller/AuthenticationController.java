package Vmo.Springpro.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.AuthenticationRequest;
import Vmo.Springpro.Dtorequest.response.AuthenticationResponse;
import Vmo.Springpro.Service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("login-in")
    ApiRespone<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var  result = authenticationService.authenticate(request);

        return ApiRespone.<AuthenticationResponse>builder()
        		.data(result)
        		.build();
    }
}
