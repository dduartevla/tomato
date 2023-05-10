package dcc.ufjf.dcc193.debora.tomato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AtividadeController {

    @Autowired
    AtividadeRepository repAtv;

    @GetMapping({"/atividades","/atividades/","/atividades/index.html"})
    public String index(){
        return "atividades-index.html";
    }

    @GetMapping("/atividades/listar.html")
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

    @GetMapping("/atividades/nova.html")
    public ModelAndView novaForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividades-form.html");
        mv.addObject("atividade", new Atividade());
        return mv;
    }

    @PostMapping("/atividades/nova.html")
    public ModelAndView novaPost(Atividade atividade){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/atividades/listar.html");
        System.out.println("nova Atividade: "+ atividade);
        repAtv.save(atividade);
        System.out.println("nova Atividade criada: "+ atividade);
        mv.addObject("atividade", new Atividade());
        return mv;    
    }
}
