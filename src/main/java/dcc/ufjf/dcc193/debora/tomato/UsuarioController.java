package dcc.ufjf.dcc193.debora.tomato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/atividades")
public class UsuarioController {

    @Autowired
    UsuarioRepository repUsuario;
    
    @GetMapping({"","/","/usuariosindex.html"})
    public String index(){
        return "usuarios/usuarios-index.html";
    }

    @GetMapping({"","/","/usuariosMenuInicial.html"})
    public String menuInicial(){
        return "/menuInicial.html";
    }
    
    @GetMapping("/novoUsuario.html")
    public ModelAndView novoUsuarioForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuarios/usuarios-form.html");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/novoUsuario.html")
    public ModelAndView novaPost(@Valid Usuario usuario, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/atividades/listarUsuarios.html");
        if(binding.hasErrors()){
            mv.setViewName("usuarios/usuarios-form.html");
            mv.addObject("usuario", usuario);
            return mv;
        }
        repUsuario.save(usuario);
        mv.addObject("atividade", new Atividade());
        return mv;    
    }
}
