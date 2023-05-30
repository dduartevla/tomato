package dcc.ufjf.dcc193.debora.tomato;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/listarUsuarios.html")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuarios/usuarios-list.html");
        List<Usuario> usuarios = repUsuario.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/editarUsuario/{id}")
    ModelAndView editarForm(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Optional<Usuario> opcional = repUsuario.findById(id);
        if (opcional.isPresent()) {
            mv.setViewName("usuarios/usuarios-form-edit.html");
            mv.addObject("usuario", opcional.get());
            return mv;
        }
        mv.setViewName("redirect:/atividades/listarUsuarios.html");
        return mv;
    }

    @PostMapping("/editarUsuario/{id}")
    ModelAndView editarFormPost(@PathVariable Long id, @Valid Usuario usuario, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuarios/usuarios-form-edit.html");
            mv.addObject("usuario", usuario);
            return mv;
        }
        repUsuario.save(usuario);
        mv.setViewName("redirect:/atividades/listarUsuarios.html");
        return mv;
    }

    @GetMapping("/excluirUsuario/{id}")
    String excluir(@PathVariable Long id){
        repUsuario.deleteById(id);    
        return "redirect:/atividades/listarUsuarios.html";
    }
}
