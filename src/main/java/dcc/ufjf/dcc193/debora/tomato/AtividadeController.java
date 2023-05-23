package dcc.ufjf.dcc193.debora.tomato;

import java.util.ArrayList;
import java.util.List;

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
public class AtividadeController {

    @Autowired
    AtividadeRepository repAtv;

    @GetMapping({"","/","/index.html"})
    public String index(){
        return "atividades-index.html";
    }

    @GetMapping("/listar.html")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividades-list.html");
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
        mv.setViewName("atividades-form.html");
        mv.addObject("atividade", new Atividade());
        return mv;
    }

    @PostMapping("/nova.html")
    public ModelAndView novaPost(@Valid Atividade atividade, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/atividades/listar.html");
        if(binding.hasErrors()){
            mv.setViewName("atividades-form.html");
            mv.addObject("atividade", atividade);
            return mv;
        }
        //System.out.println("nova Atividade: "+ atividade);
        repAtv.save(atividade);
        //System.out.println("nova Atividade criada: "+ atividade);
        mv.addObject("atividade", new Atividade());
        return mv;    
    }
}
