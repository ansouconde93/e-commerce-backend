package backend.Services;
import java.util.ArrayList;
import java.util.Collection;

import backend.DAO.ClientRepository;
import backend.Entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

    @Autowired
    private ClientRepository clientRepository;
    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(email);
        if(client == null) throw  new UsernameNotFoundException("Client n'existe pas");

        //Preparer les roles de l'utilisateur sous forme de collection d'objets compressible par spring security
        Collection<GrantedAuthority> authorisations = new ArrayList<GrantedAuthority>();
        client.getRoles().forEach(r->{
            authorisations.add(new SimpleGrantedAuthority(r.getNomrole()));
        });
        return new User(client.getUsername(),client.getPassword(),authorisations);
    }
}
