package backend.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.DAO.ClientRepository;
import backend.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Filtre d'extraction de l'utilisateurs
 */
public class EcomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientRepository clientRepository;

    public EcomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    /*
     * Methode d'extraction de l'utilisateur
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Client client = null;
        try {
            client = new ObjectMapper().readValue(request.getInputStream(), Client.class);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User springUser =(User) authResult.getPrincipal();

        //acces token definition
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())//on peut mettre tout ce qu'on veut
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstante.EXPIRATION_TIME))
                .setIssuer(request.getRequestURI().toString())
                .signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        //refresh token definition
        String refreshToken = Jwts.builder()
                .setSubject(springUser.getUsername())//on peut mettre tout ce qu'on veut
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstante.EXPIRATION_TIME_REFRESH_TOKEN))
                .setIssuer(request.getRequestURI().toString())
                .signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
                .compact();
        response.addHeader(SecurityConstante.HEADER_STRING, SecurityConstante.TOKEN_PREFIX+jwtToken);
        response.addHeader(SecurityConstante.HEADER_REFRESH_TOKEN, SecurityConstante.TOKEN_PREFIX+refreshToken);
    }
}

