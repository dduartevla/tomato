package dcc.ufjf.dcc193.debora.tomato;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests()
        .requestMatchers("/**css").permitAll()
        .requestMatchers("/usuarios/usuarios-form.html").permitAll()
        .requestMatchers("/**").authenticated();

        http.formLogin()
        .loginPage("usuarios/usuarios-login.html")
        .loginProcessingUrl("/login")
        .successForwardUrl("/atividades").permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
        return http.build();
    }
    
}
