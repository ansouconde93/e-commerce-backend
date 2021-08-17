package backend.Services;

import backend.DAO.ClientRepository;
import backend.DAO.RolesRepository;
import backend.Entities.Client;
import backend.Entities.Roles;
import backend.modele.Tokens;
import backend.security.SecurityConstante;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImp implements ClientService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Client saveClient(Client client) {
        Client user = clientRepository.findByUsername(client.getUsername());
        Client c = new Client();
        if (user == null){
            String passwordEncoder = bCryptPasswordEncoder.encode(client.getPassword());
            //add roles in db
            client.getRoles().forEach(r->{
                Roles role = rolesRepository.findByNomrole(r.getNomrole());
                if (role ==null){
                    r.setId(null);
                    role = rolesRepository.save(r);
                    c.getRoles().add(role);
                }
            });
            client.setPassword(passwordEncoder);
            Client u = clientRepository.save(client);
            //add role to user
            u.setRoles(c.getRoles());
            return u;
        }
        return user;
    }

    @Override
    public Tokens generateAccessTokenFromRefreshToken(String userRefreshToken) {
            //recuperer l'entête du token
            String refreshToken = userRefreshToken;
            if (refreshToken == null || !refreshToken.startsWith(SecurityConstante.TOKEN_PREFIX)) {
                return null;
            }
            try {
                Claims claims = Jwts.parser()//recuperer les revendications
                        .setSigningKey(SecurityConstante.SECRET)
                        .parseClaimsJws(refreshToken.replace(SecurityConstante.TOKEN_PREFIX, ""))
                        .getBody();

                String useremail = claims.getSubject();//lecture de l'email(username) de l'utilisateur qui a envoyer la requet

                //get client from database
                Client client = clientRepository.findByUsername(useremail);
                //recupérer les roles du client
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                client.getRoles().forEach(r -> {
                    authorities.add(new SimpleGrantedAuthority(r.getNomrole()));
                });

                //acces token definition
                String jwtToken = Jwts.builder()
                        .setSubject(client.getUsername())//on peut mettre tout ce qu'on veut
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstante.EXPIRATION_TIME))
                        .setIssuer("/client/token/refresh")
                        .signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
                        .claim("roles", authorities)
                        .compact();
                //refresh token definition
                String refresh_token = Jwts.builder()
                        .setSubject(client.getUsername())//on peut mettre tout ce qu'on veut
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstante.EXPIRATION_TIME_REFRESH_TOKEN))
                        .setIssuer("/client/token/refresh")
                        .signWith(SignatureAlgorithm.HS256, SecurityConstante.SECRET)
                        .compact();
                return new Tokens(SecurityConstante.TOKEN_PREFIX + jwtToken,SecurityConstante.TOKEN_PREFIX + refresh_token);
            } catch (Exception exception) {
                return null;
            }
    }

    @Override
    public Client getClientByUsername(String username) {
        Client client = new Client();
        client.setName(clientRepository.findByUsername(username).getName());
        client.setUsername(clientRepository.findByUsername(username).getUsername());
        client.setAddress(clientRepository.findByUsername(username).getAddress());
        client.setCountry(clientRepository.findByUsername(username).getCountry());
        client.setPhoneNumber(clientRepository.findByUsername(username).getPhoneNumber());
        client.setZipCode(clientRepository.findByUsername(username).getZipCode());
        return client;
    }
}

