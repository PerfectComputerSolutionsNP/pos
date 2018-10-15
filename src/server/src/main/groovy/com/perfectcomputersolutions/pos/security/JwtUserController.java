package com.perfectcomputersolutions.pos.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(
        value = "jwtUserController",
        description =
                "This controller's sole purpose it to return the current user's user details " +
                "such as username, email, and user roles. This endpoint does not require any particular roles"
)
public class JwtUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ApiOperation(value = "Returns current user's user details. This operation does not requires any role.")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {

        String  token    = request.getHeader(tokenHeader).substring(7);
        String  username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user     = (JwtUser) userDetailsService.loadUserByUsername(username);

        return user;
    }

}