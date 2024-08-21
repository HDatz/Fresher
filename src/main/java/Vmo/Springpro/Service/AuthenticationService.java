package Vmo.Springpro.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import Vmo.Springpro.Dtorequest.AuthenticationRequest;
import Vmo.Springpro.Dtorequest.IntrospectRequest;
import Vmo.Springpro.Dtorequest.response.AuthenticationResponse;
import Vmo.Springpro.Dtorequest.response.IntrospectResponse;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;
import Vmo.Springpro.repository.FresherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    FresherRepository fresherRepository; 
    
    @NonFinal
    @Value("${jwt.signer-key}")
    private String SIGNER_KEY;
    
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
    	var token = request.getToken();
    	
    	JWSVerifier verfirer = new MACVerifier(SIGNER_KEY.getBytes());
    	
    	SignedJWT signedJWT = SignedJWT.parse(token);
    	
    	Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
    	
    	var verified = signedJWT.verify(verfirer);
    	
    	return IntrospectResponse.builder()
    			.valid(verified && expityTime.after(new Date()))
    			.build();
    }
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var fresher = fresherRepository.findByName(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorClass.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), fresher.getPassword());
        
        if(!authenticated )
        	throw new AppException(ErrorClass.UNAUTHENTICATED);
        
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
        		.token(token)
                .authenticated(authenticated)
                .build();
    }
    
    private String generateToken(String username) {
    	JWSHeader hearder = new JWSHeader(JWSAlgorithm.HS512);
    	
    	JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
    			.subject(username)
    			.issuer("Vmo.com")
    			.issueTime(new Date())
    			.expirationTime(new Date(
    					Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()
    			))
    			.claim("customerClaim", "Customor")
    			.build();    
    	
    	Payload payload = new Payload(jwtClaimSet.toJSONObject());
    	JWSObject jwsObject = new JWSObject(hearder,payload);
    	
    	try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			log.error("Cannot creater token ", e);
			throw new RuntimeException(e);
		}
    }
}
