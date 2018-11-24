package com.perfectcomputersolutions.pos.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@Api(value="category",
        description=
                "All operations pertaining to user authentication occurs here. There are not particular "     +
                "access rights required for any of the end points because this is the endpoint that is "      +
                "responsible for determining whether or not a user is valid or not. All HTTP requests will " +
                "automatically be forwarded to this endpoint unless otherwise specified."
)
public class JwtAuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @ApiOperation(value = "Creates and returns an authentication token. This operation does not requires any role.")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws JwtAuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final JwtUser user  = (JwtUser)userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String  token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token, user));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    @ApiOperation(value = "Gets and refreshes the current user's authentication token. This operation does not requires any role.")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {

        String authToken   = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username    = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user       = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {

            String refreshedToken = jwtTokenUtil.refreshToken(token);

            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken, user));

        } else {

            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({JwtAuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(JwtAuthenticationException e) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link JwtAuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException exception) {

            throw new JwtAuthenticationException("User is disabled!", exception);

        } catch (BadCredentialsException e) {

            throw new JwtAuthenticationException("Bad credentials!", e);
        }
    }
}