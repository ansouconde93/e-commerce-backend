package backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.formLogin();

        //definir son propre formulaire d'authentification
        //http.formLogin().loginPage("/myLoginForm.html");

        //Desactivation de la session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //donner la permission à tous les utilisateurs de s'authentifier ou de s'enregistrer
        http.authorizeRequests().antMatchers(
                "/login/**","/client/save","/client/token/refresh",
                "/categories","/category/{id}",
                "/product/photo/{id}","/product/keyword/","/product/avalable","/product/promotion",
                "/product/selected","/product/{id}","/products","/contactus").permitAll();

        //donner les permissions
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/product/save").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/product/delete/{id}").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/uploadPhotoProduct/{id}").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/category/delete/{id}").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/category/save").hasAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/orders/{actionToDo}")
                .hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/client")
                .hasAnyAuthority("admin","user");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new EcomAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new EcomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}

