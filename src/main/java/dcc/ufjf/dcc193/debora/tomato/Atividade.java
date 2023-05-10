package dcc.ufjf.dcc193.debora.tomato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message="É preciso um título!")
    private String titulo;
    @PositiveOrZero(message="Não pode ser negativo!")
    private Integer tomatos;    
    
    public Atividade() {
        this(null,null,0);
    }

    public Atividade(String titulo) {
        this(null, titulo, 0);
    }

    public Atividade(Long id, String titulo, Integer tomatos) {
        this.id = id;
        this.tomatos = tomatos;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Atividade [id=" + id + ", titulo=" + titulo + ", tomatos=" + tomatos + "]";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getTomatos() {
        return tomatos;
    }
    public void setTomatos(Integer tomatos) {
        this.tomatos = tomatos;
    }
    
    
}
