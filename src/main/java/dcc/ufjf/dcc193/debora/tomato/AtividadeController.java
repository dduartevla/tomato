package dcc.ufjf.dcc193.debora.tomato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AtividadeController {
    @GetMapping({"/atividades","/atividades/","/atividades/index.html"})
    public String index(){
        return "atividades-index.html";
    }

    @GetMapping("/atividades/listar.html")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividades-list.html");
        List<Atividade> atividades = new ArrayList<>(){
            {
                add(new Atividade("Teste 1"));
                add(new Atividade("Teste 2"));
                add(new Atividade("Teste 3"));
                add(new Atividade("Teste 4"));
            }
        };
        mv.addObject("atividades", atividades);
        return mv;
    }


}
