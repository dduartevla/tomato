package dcc.ufjf.dcc193.debora.tomato;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atividades")
public class UsuarioController {
    
    @GetMapping({"","/","/usuariosindex.html"})
    public String index(){
        return "usuarios/usuarios-index.html";
    }
}
