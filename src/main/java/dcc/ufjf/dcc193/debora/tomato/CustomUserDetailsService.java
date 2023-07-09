package dcc.ufjf.dcc193.debora.tomato;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthorityException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRep.findByEmail(email);

        if (usuario != null) {
            return new User(usuario.getEmail(),
                    usuario.getSenha(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário ou senha inválidos!");
        }
    }
}
