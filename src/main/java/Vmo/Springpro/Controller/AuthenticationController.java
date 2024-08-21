package Vmo.Springpro.Controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.AuthenticationRequest;
import Vmo.Springpro.Dtorequest.IntrospectRequest;
import Vmo.Springpro.Dtorequest.response.AuthenticationResponse;
import Vmo.Springpro.Dtorequest.response.IntrospectResponse;
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
    
    @PostMapping("/token")
    ApiRespone<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var  result = authenticationService.authenticate(request);

        return ApiRespone.<AuthenticationResponse>builder()
        		.data(result)
        		.build();
    }
    
    @PostMapping("/instrospect")
    ApiRespone<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) 
    	throws JOSEException, ParseException {
        	var  result = authenticationService.introspect(request);

        	return ApiRespone.<IntrospectResponse>builder()
        		.data(result)
        		.build();
    }
}
