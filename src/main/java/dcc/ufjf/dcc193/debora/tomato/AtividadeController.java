package dcc.ufjf.dcc193.debora.tomato;

import java.util.ArrayList;
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
public class AtividadeController {

    @Autowired
    AtividadeRepository repAtv;

    @GetMapping({"","/","/index.html"})
    public String index(){
        return "pastaAtividades/atividades-index.html";
    }

    @GetMapping("/listar.html")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pastaAtividades/atividades-list.html");
        List<Atividade> atividades = repAtv.findAll();
        /*{
            {
                add(new Atividade("Teste 1"));
                add(new Atividade("Teste 2"));
                add(new Atividade("Teste 3"));
                add(new Atividade("Teste 4"));
            }
        };*/
        mv.addObject("atividades", atividades);
        return mv;
    }

    @GetMapping("/nova.html")
    public ModelAndView novaForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pastaAtividades/atividades-form.html");
        mv.addObject("atividade", new Atividade());
        return mv;
    }

    @PostMapping("/nova.html")
    public ModelAndView novaPost(@Valid Atividade atividade, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/atividades/listar.html");
        if(binding.hasErrors()){
            mv.setViewName("pastaAtividades/atividades-form.html");
            mv.addObject("atividade", atividade);
            return mv;
        }
        //System.out.println("nova Atividade: "+ atividade);
        repAtv.save(atividade);
        //System.out.println("nova Atividade criada: "+ atividade);
        mv.addObject("atividade", new Atividade());
        return mv;    
    }

    @GetMapping("/editar/{id}")
    ModelAndView editarForm(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();
        Optional<Atividade> opcional = repAtv.findById(id);
        if (opcional.isPresent()) {
            mv.setViewName("pastaAtividades/atividades-form-edit.html");
            mv.addObject("atividade", opcional.get());
            return mv;
        }
        mv.setViewName("redirect:/atividades/listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    ModelAndView editarFormPost(@PathVariable Long id, @Valid Atividade atividade, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("pastaAtividades/atividades-form-edit.html");
            mv.addObject("atividade", atividade);
            return mv;
        }
        repAtv.save(atividade);
        mv.setViewName("redirect:/atividades/listar.html");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    String excluir(@PathVariable Long id){
        repAtv.deleteById(id);    
        return "redirect:/atividades/listar.html";
    }
}
